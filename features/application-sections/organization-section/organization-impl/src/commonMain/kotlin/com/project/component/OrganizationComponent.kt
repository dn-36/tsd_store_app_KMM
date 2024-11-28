package com.project.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.project.chats.ChatScreensApi
import com.project.chats.ProfileScreensApi
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.menu_bottom_bar.ui.MenuBottomBar
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.menu.screen.OrganizationScreenApi
import com.project.menu.screen.TapeScreenApi
import com.project.network.Navigation
import com.project.viewmodel.OrganizationsIntents
import com.project.viewmodel.OrganizationsViewModel
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.plus
import project.core.resources.update_pencil

class OrganizationComponent ( override val viewModel: OrganizationsViewModel ) : NetworkComponent {

    @Composable
    override fun Component () {

        val organizationScreens : OrganizationScreenApi = KoinPlatform.getKoin().get()
        val chatsScreens : ChatScreensApi = KoinPlatform.getKoin().get()
        val menuCrmScreens : MenuCrmScreenApi = KoinPlatform.getKoin().get()
        val tapeScreens : TapeScreenApi = KoinPlatform.getKoin().get()
        val profileScreens : ProfileScreensApi = KoinPlatform.getKoin().get()

        Navigation.navigator = LocalNavigator.currentOrThrow

        val scope = rememberCoroutineScope()

        viewModel.processIntent(OrganizationsIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)){

            Column(modifier = Modifier.fillMaxSize()

                .padding(16.dp)) {

                Text("Организации", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(20.dp))

                SearchComponent( onValueChange = { text -> viewModel.processIntent(

                    OrganizationsIntents.InputTextSearchComponent(text)) } ).Content()

                LazyColumn ( modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f)  ) {

                    itemsIndexed( viewModel.state.listFilteredOrganizations ) {

                    index, item ->

                        Box(modifier = Modifier.pointerInput(true) {

                            detectTapGestures(

                                onPress = {
                                    if (  viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index] == 1f ) {

                                        viewModel.processIntent(OrganizationsIntents.OnePressItem)
                                    }
                                },

                                onLongPress = {

                                    viewModel.processIntent(

                                        OrganizationsIntents.LongPressItem(index))

                                },
                            )

                        }) {

                            Row(verticalAlignment = Alignment.CenterVertically,

                                modifier = Modifier.fillMaxWidth()

                                    .padding(vertical = 8.dp)) {

                                Box(
                                    modifier = Modifier.size(30.dp).clip(CircleShape)
                                        .background(

                                            viewModel.state.listColorActiveOrganizations[index])

                                        .clickable(

                                            indication = null, // Отключение эффекта затемнения

                                            interactionSource = remember {

                                                MutableInteractionSource() })
                                        {
                                            if ( viewModel.state.isVisibilityDeleteComponent == 0f

                                                ) { viewModel.processIntent(

                                                    OrganizationsIntents.ChoosingActiveOrganization(

                                                        scope, item.company?.ui?:""
                                                    )
                                                )

                                            }

                                        }
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Column {

                                    Text(
                                        item.company?.name?:"",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    if ( item.company?.url == null || item.company?.url!!.isBlank() ){

                                        Text(
                                            "https://delta.online/${item.company?.ui}",
                                            fontSize = 12.sp,
                                            color = Color.Blue
                                        )

                                    }

                                    else {

                                        Text(
                                            "https://delta.online/${item.company?.url}",
                                            fontSize = 12.sp,
                                            color = Color.Blue
                                        )

                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Box(
                                        modifier = Modifier.height(1.dp).fillMaxWidth()
                                            .background(Color.LightGray)
                                    )

                                }
                            }

                            if ( item.company?.name != "Личный профиль" && item.company?.name != "c17" ) {

                                if (  viewModel.state.listAlphaTools.size > index &&

                                    viewModel.state.listAlphaTools[index] == 1f ) {

                                    Row(modifier = Modifier.align(Alignment.TopEnd)) {

                                        Image(painter = painterResource(Res.drawable.update_pencil),
                                            contentDescription = null,
                                            modifier = Modifier.size(17.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })
                                                {
                                                    if (viewModel.state.isVisibilityDeleteComponent == 0f) {

                                                        viewModel.processIntent(
                                                            OrganizationsIntents.SelectItemUpdate(
                                                                item
                                                            )
                                                        )
                                                    }

                                                })

                                        Spacer(modifier = Modifier.width(20.dp))

                                        Image(painter = painterResource(Res.drawable.cancel),
                                            contentDescription = null,
                                            modifier = Modifier.size(15.dp)
                                                .clickable(
                                                    indication = null,
                                                    interactionSource = remember { MutableInteractionSource() })
                                                {
                                                    viewModel.processIntent(
                                                        OrganizationsIntents.OpenDeleteComponent(
                                                            item
                                                        )
                                                    )
                                                })
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Column(horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.BottomCenter)) {

                if ( (viewModel.state.listAlphaTools.find { it == 1f }) ==  null ) {

                    Box(
                        modifier = Modifier.padding(16.dp)
                            .size(60.dp).clip(CircleShape).background(Color.White).border(

                                width = 2.dp, color = Color.Black, shape = CircleShape

                            ), contentAlignment = Alignment.Center
                    ) {

                        Image(painter = painterResource(Res.drawable.plus),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {
                                    if (viewModel.state.isVisibilityDeleteComponent == 0f) {

                                        viewModel.processIntent(OrganizationsIntents.OpenWindowAddOrganization)

                                    }

                                })
                    }
                }

                    Box(modifier = Modifier) {

                        MenuBottomBar().init(
                            menuCrmScreens.MenuCrm(),
                            profileScreens.profile(),
                            organizationScreens.organization(),
                            chatsScreens.chatsScreen(),
                            tapeScreens.tape(),
                        ).Content(MenuBottomBarSection.ORGANIZATION)
                    }
            }
        }

        if ( viewModel.state.isVisibilityDeleteComponent == 1f ) {

            DeleteComponent(

                name = "организацию",

                onClickNo = {

                viewModel.processIntent(OrganizationsIntents.NoDelete) },

                onClickDelete = { viewModel.processIntent(

                    OrganizationsIntents.DeleteOrganization ( scope ) ) }).Content()

        }

    }
}