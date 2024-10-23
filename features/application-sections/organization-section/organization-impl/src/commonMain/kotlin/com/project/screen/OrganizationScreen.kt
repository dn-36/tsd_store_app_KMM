package com.project.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.project.menu.screen.OrganizationScreenApi
import com.project.chats.ChatScreensApi
import com.project.chats.ProfileScreensApi
import com.project.network.Navigation
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.menu.screen.TapeScreenApi
import com.project.viewmodel.OrganizationsIntents
import com.project.viewmodel.OrganizationsViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.plus

class OrganizationScreen(): Screen {

 //   val n:OrganizationClientApi = OrganizationClientImpl(OrganizationsApi)

    val vm:OrganizationsViewModel = getKoin().get()


    @Composable
    override fun Content() {

        val organizationScreens : OrganizationScreenApi = getKoin().get()
        val chatsScreens : ChatScreensApi = getKoin().get()
        val menuCrmScreens : MenuCrmScreenApi = getKoin().get()
        val tapeScreens : TapeScreenApi = getKoin().get()
        val profileScreens : ProfileScreensApi = getKoin().get()

        Navigation.navigator = LocalNavigator.currentOrThrow

        val scope = rememberCoroutineScope()

        vm.processIntent(OrganizationsIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Организации", color = Color.Black, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn { itemsIndexed(vm.organizationsState.allOrganizations){index,item ->

                    Box() {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 8.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            {
                                vm.processIntent(
                                    OrganizationsIntents.ChoosingActiveOrganization(
                                        scope,
                                        item.company!!.ui!!
                                    )
                                )
                            }) {

                            Box(
                                modifier = Modifier.size(30.dp).clip(CircleShape)
                                    .background(vm.organizationsState.listColorActiveOrganizations[index])
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(
                                modifier = Modifier.height(60.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    item.company!!.name!!,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text("${item.company!!.url}", fontSize = 12.sp, color = Color.Blue)

                                Box(
                                    modifier = Modifier.height(1.dp).fillMaxWidth(0.9f)
                                        .background(Color.LightGray)
                                )

                            }
                        }
                            Image(painter = painterResource(Res.drawable.cancel),
                                contentDescription = null,
                                modifier = Modifier.size(10.dp).align(Alignment.TopEnd).clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })
                                { vm.processIntent(OrganizationsIntents.DeleteOrganization(scope, item.company!!.ui!!))
                                })
                    }
                }
              }
            }

            Column(horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.BottomCenter)) {

                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.padding(16.dp).size(70.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { vm.processIntent(OrganizationsIntents.OpenWindowAddOrganization)})

                Box(modifier = Modifier) {
                    MenuBottomBar().init(
                        menuCrmScreens.MenuCrm(),
                        profileScreens.profile(),
                        organizationScreens.organization(),
                        chatsScreens.chatsScreen(),
                        tapeScreens.tape(),
                    ).Content()
                }
            }
        }
    }
}