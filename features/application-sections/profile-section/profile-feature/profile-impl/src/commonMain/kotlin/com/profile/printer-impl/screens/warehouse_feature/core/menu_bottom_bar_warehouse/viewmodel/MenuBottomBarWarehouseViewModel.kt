package com.profile.`printer-impl`.screens.warehouse_feature.core.menu_bottom_bar_warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseIntents
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseState
import com.profile.profile.screens.profile_screen.screen.ProfileScreen
import org.example.project.presentation.profile_feature.warehouse_feature.finance_feature.screen.FinanceScreen
import com.profile.profile.screens.warehouse_feature.main_refactor.screen.WarehouseScreen
import com.project.`printer-api`.PrinterScreensApi
import com.project.network.Navigation
import org.koin.mp.KoinPlatform.getKoin

class MenuBottomBarWarehouseViewModel : ViewModel() {

    var menuBottomBarWarehouseState by mutableStateOf(MenuBottomBarWarehouseState())
    val printerScreen: PrinterScreensApi = getKoin().get()

    fun processIntent(intent: MenuBottomBarWarehouseIntents){
        when(intent){
            is MenuBottomBarWarehouseIntents.Finance -> { finance() }

            is MenuBottomBarWarehouseIntents.Print -> {  Navigation.navigator.push(printerScreen.printer())  }

            is MenuBottomBarWarehouseIntents.Warehouse -> { warehouse() }

            is MenuBottomBarWarehouseIntents.Profile -> { profile() }
        }
    }

    fun warehouse() {
        val newList = mutableListOf(
            Color(0xFFFF9800),
            Color.Transparent,
            Color.Transparent,
            Color.Transparent)

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(WarehouseScreen)

    }

    fun finance() {
        val newList = mutableListOf(
            Color.Transparent,
            Color(0xFFFF9800),
            Color.Transparent,
            Color.Transparent)

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(FinanceScreen)

    }

    fun print() {
        val newList = mutableListOf(
            Color.Transparent,
            Color.Transparent,
            Color(0xFFFF9800),
            Color.Transparent)

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(WarehouseScreen)

    }

    fun profile() {
        val newList = mutableListOf(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color(0xFFFF9800))

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(ProfileScreen)

    }

}