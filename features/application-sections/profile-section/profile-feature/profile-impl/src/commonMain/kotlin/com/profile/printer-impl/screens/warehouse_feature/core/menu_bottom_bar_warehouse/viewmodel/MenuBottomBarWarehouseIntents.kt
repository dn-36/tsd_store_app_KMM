package org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel


sealed class MenuBottomBarWarehouseIntents {
    object Finance: MenuBottomBarWarehouseIntents()
    object Warehouse: MenuBottomBarWarehouseIntents()
    object Print: MenuBottomBarWarehouseIntents()
    object Profile: MenuBottomBarWarehouseIntents()
}