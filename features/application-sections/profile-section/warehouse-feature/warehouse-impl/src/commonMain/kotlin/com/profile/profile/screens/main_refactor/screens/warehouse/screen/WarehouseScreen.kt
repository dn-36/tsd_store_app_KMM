package com.profile.profile.screens.main_refactor.screens.warehouse.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen.ArrivalAndConsumptionScreen
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseIntents
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseViewModel

import com.project.chats.ProfileScreensApi
import com.project.chats.WarehouseScreensApi
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.`printer-api`.PrinterScreensApi
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.plus

class WarehouseScreen: Screen {
    val vm = WarehouseViewModel()
    @Composable
    override fun Content() {

        val warehouseScreens : WarehouseScreensApi = getKoin().get()

        val profileScreens : ProfileScreensApi = getKoin().get()

        val printScreen:PrinterScreensApi = getKoin().get()

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Склад", color = Color.Black, fontSize = 20.sp)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {

                Column(horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.BottomCenter)) {
                    Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                        modifier = Modifier.padding(16.dp).size(70.dp)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            {vm.processIntents(WarehouseIntents.AddWarehouse(scope))})

                    MenuBottomBarWarehouse().init(
                        warehouseScreens.warehouse(),
                        profileScreens.profile(),
                        ArrivalAndConsumptionScreen(),
                        printScreen.printer()
                    ).Content()
                }
            }
        }
    }
}