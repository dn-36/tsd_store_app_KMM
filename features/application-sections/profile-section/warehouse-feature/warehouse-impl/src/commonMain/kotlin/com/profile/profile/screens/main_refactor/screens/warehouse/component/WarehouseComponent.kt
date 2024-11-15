package com.profile.profile.screens.main_refactor.screens.warehouse.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
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
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen.ArrivalAndConsumptionScreen
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseIntents
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseViewModel
import com.project.chats.ProfileScreensApi
import com.project.chats.WarehouseScreensApi
import com.project.core_app.components.DeleteComponent
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.`printer-api`.PrinterScreensApi
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.plus
import project.core.resources.update_pencil

class WarehouseComponent ( override val viewModel: WarehouseViewModel ) : NetworkComponent {

    @Composable
    override fun Component () {

        val warehouseScreens: WarehouseScreensApi = KoinPlatform.getKoin().get()

        val profileScreens: ProfileScreensApi = KoinPlatform.getKoin().get()

        val printScreen: PrinterScreensApi = KoinPlatform.getKoin().get()

        val scope = rememberCoroutineScope()

        viewModel.processIntents(WarehouseIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Склад", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn ( modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f) ) {
                    items(viewModel.state.listAllWarehouse) { item ->
                        if(item.stores.isNotEmpty()) {
                            item.stores.forEach {
                                Box() {
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 8.dp).clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })
                                            {}) {

                                        /*Box(
                                            modifier = Modifier.size(30.dp).clip(CircleShape)
                                                .background(Color.Gray)
                                        )*/

                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column(
                                            modifier = Modifier.height(60.dp),
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {

                                            Text(
                                                it!!.name?:"Нет имени",
                                                fontSize = 17.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            Text(
                                                item.name?:"Нет имени",
                                                fontSize = 17.sp,
                                            )

                                            Box(
                                                modifier = Modifier.height(1.dp).fillMaxWidth()
                                                    .background(Color.LightGray)
                                            )

                                        }
                                    }
                                    Row(modifier = Modifier.align(Alignment.TopEnd)) {

                                        Image(painter = painterResource(Res.drawable.update_pencil),
                                            contentDescription = null,
                                            modifier = Modifier.size(17.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember {

                                                        MutableInteractionSource() })
                                                {
                                                    if ( viewModel.state.isVisibilityDeleteComponent == 0f ) {

                                                    viewModel.processIntents(WarehouseIntents.

                                                    OpenWindowUpdateWarehouse(scope,item))}})


                                        Spacer(modifier = Modifier.width(20.dp))

                                        Image(painter = painterResource(Res.drawable.cancel),
                                            contentDescription = null,
                                            modifier = Modifier.size(15.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })
                                                {
                                                    viewModel.processIntents(
                                                        WarehouseIntents.OpenDeleteComponent(

                                                            ui = it!!.ui

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

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {

                Box( modifier = Modifier.padding(16.dp)
                    .size(60.dp).clip(CircleShape).background(Color.White).border(

                        width = 2.dp, color = Color.Black, shape = CircleShape

                    )
                    , contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                        modifier = Modifier.size(60.dp)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            {
                                if ( viewModel.state.isVisibilityDeleteComponent == 0f ) {

                                viewModel.processIntents(WarehouseIntents.OpenWindowAddWarehouse(

                                    scope)) }})
                }

                MenuBottomBarWarehouse().init(
                    warehouseScreens.warehouse(),
                    profileScreens.profile(),
                    ArrivalAndConsumptionScreen(),
                    printScreen.printer()

                ).Content(MenuBottomBarWarehouseSection.WAREHOUSE)
            }

        }

        if ( viewModel.state.isVisibilityDeleteComponent == 1f ) {

            DeleteComponent(

                name = "склад",

                onClickDelete = { scope ->
                    viewModel.processIntents(

                        WarehouseIntents.DeleteWarehouse(scope)

                    )
                },
                onClickNo = { viewModel.processIntents(WarehouseIntents.NoDelete) }).Content()

        }

    }

}