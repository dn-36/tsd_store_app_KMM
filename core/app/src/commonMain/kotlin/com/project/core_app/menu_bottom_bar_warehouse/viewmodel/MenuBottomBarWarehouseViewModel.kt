package com.profile.profile.screens.warehouse_feature.core.menu_bottom_bar_warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseIntents
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseState
import com.project.network.Navigation

class MenuBottomBarWarehouseViewModel : ViewModel() {

    var menuBottomBarWarehouseState by mutableStateOf(MenuBottomBarWarehouseState())

    fun processIntent(intent: MenuBottomBarWarehouseIntents){
        when(intent){
            is MenuBottomBarWarehouseIntents.Finance -> { finance(intent.screen) }

            is MenuBottomBarWarehouseIntents.Print -> { print(intent.screen) }

            is MenuBottomBarWarehouseIntents.Warehouse -> { warehouse(intent.screen) }

            is MenuBottomBarWarehouseIntents.Profile -> { profile(intent.screen) }
        }
    }

    fun warehouse(screen:Screen) {
        val newList = mutableListOf(
            Color(0xFFFF9800),
            Color.Transparent,
            Color.Transparent,
            Color.Transparent)

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(screen)

    }

    fun finance(screen: Screen) {
        val newList = mutableListOf(
            Color.Transparent,
            Color(0xFFFF9800),
            Color.Transparent,
            Color.Transparent)

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(screen)

    }

    fun print(screen:Screen) {
        val newList = mutableListOf(
            Color.Transparent,
            Color.Transparent,
            Color(0xFFFF9800),
            Color.Transparent)

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(screen)

    }

    fun profile(screen:Screen) {
        val newList = mutableListOf(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color(0xFFFF9800))

        menuBottomBarWarehouseState = menuBottomBarWarehouseState.copy(
            colorListBottomMenu = newList
        )
        Navigation.navigator.push(screen)

    }

}