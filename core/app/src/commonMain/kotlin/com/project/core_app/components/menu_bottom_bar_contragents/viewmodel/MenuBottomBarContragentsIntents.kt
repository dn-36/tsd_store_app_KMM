package com.project.core_app.components.menu_bottom_bar_contragents.viewmodel

import cafe.adriel.voyager.core.screen.Screen


sealed class MenuBottomBarContragentsIntents {

    data class Contragents(val screen: Screen): MenuBottomBarContragentsIntents()

    data class Locations(val screen: Screen): MenuBottomBarContragentsIntents()

    data class SetScreen(val section: MenuBottomBarContragentsSection): MenuBottomBarContragentsIntents()

}