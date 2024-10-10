package com.profile.profile.screens.warehouse_feature.main_refactor.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.profile.profile.screens.profile_screen.screens.finance.FinanceScreen
import com.profile.profile.screens.profile_screen.screens.print.PrintScreen

import com.project.chats.ProfileScreensApi
import com.project.chats.WarehouseScreensApi
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.`printer-api`.PrinterScreensApi
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.koin.mp.KoinPlatform.getKoin

class WarehouseScreen: Screen {
    @Composable
    override fun Content() {

        val warehouseScreens : WarehouseScreensApi = getKoin().get()

        val profileScreens : ProfileScreensApi = getKoin().get()
        val printScreen:PrinterScreensApi = getKoin().get()
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Склад", color = Color.Black, fontSize = 20.sp)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {

                MenuBottomBarWarehouse().init(
                    warehouseScreens.warehouse(),
                    profileScreens.profile(),
                    FinanceScreen(),
                    printScreen.printer()

                ).Content()

            }
        }
    }
}