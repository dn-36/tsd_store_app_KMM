package com.project.core_app.components.menu_bottom_tools.viewmodel

import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection


sealed class MenuBottomBarToolsIntents {
  //  data class Finance(val screen: Screen): MenuBottomBarToolsIntents()
    object/*data class*/ Tools/*(val screen: Screen)*/: MenuBottomBarToolsIntents()
    object/*data class*/ Print/*(val screen: Screen)*/: MenuBottomBarToolsIntents()
   // data class Profile(val screen: Screen): MenuBottomBarToolsIntents()
    data class SetScreen(val section: MenuBottomBarToolsSection): MenuBottomBarToolsIntents()

}