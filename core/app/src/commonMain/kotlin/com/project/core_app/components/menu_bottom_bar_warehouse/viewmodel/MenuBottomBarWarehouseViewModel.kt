package com.project.core_app.components.menu_bottom_bar_warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.components.menu_bottom_bar_warehouse.viewmodel.MenuBottomBarWarehouseIntents
import com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarToolsIntents
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseState
import com.project.network.Navigation
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection

class MenuBottomBarWarehouseViewModel : ViewModel() {

    var state by mutableStateOf(MenuBottomBarWarehouseState( MenuBottomBarWarehouseSection.WAREHOUSE ))

    private var setUsed:Boolean = false

    fun processIntent(intent: MenuBottomBarWarehouseIntents){
        when(intent){
            is MenuBottomBarWarehouseIntents.Finance -> { finance(intent.screen) }

            is MenuBottomBarWarehouseIntents.Print -> { print(intent.screen) }

            is MenuBottomBarWarehouseIntents.Warehouse -> { warehouse(intent.screen) }

            is MenuBottomBarWarehouseIntents.Profile -> { profile(intent.screen) }

            is MenuBottomBarWarehouseIntents.SetScreen -> { if(!setUsed) {
                setUsed = true
                setScreen( intent.section ) } }
        }
    }

    fun warehouse(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    fun finance(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    fun print(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    fun profile(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    fun setScreen(section: MenuBottomBarWarehouseSection){

        state  = state.copy(section)

    }

}