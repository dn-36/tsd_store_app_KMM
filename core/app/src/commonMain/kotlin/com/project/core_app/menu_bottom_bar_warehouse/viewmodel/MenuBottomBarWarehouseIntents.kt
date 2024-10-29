package org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel

import cafe.adriel.voyager.core.screen.Screen
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse


sealed class MenuBottomBarWarehouseIntents {
    data class Finance(val screen: Screen): MenuBottomBarWarehouseIntents()
    data class Warehouse(val screen: Screen): MenuBottomBarWarehouseIntents()
    data class Print(val screen: Screen): MenuBottomBarWarehouseIntents()
    data class Profile(val screen: Screen): MenuBottomBarWarehouseIntents()
    data class SetScreen(val section: MenuBottomBarWarehouseSection): MenuBottomBarWarehouseIntents()

}