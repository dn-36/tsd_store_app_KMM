package com.project.core_app.menu_bottom_bar.viewmodel

import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.menu_bottom_bar.ui.Section

sealed class MenuBottomBarIntents {
    data class CRM(val screen: Screen): MenuBottomBarIntents()
    data class Profile(val screen: Screen): MenuBottomBarIntents()
    data class Home(val screen: Screen): MenuBottomBarIntents()
    data class Chats(val screen: Screen): MenuBottomBarIntents()
    data class Tape(val screen: Screen): MenuBottomBarIntents()
    data class SetScreen(val section:Section):MenuBottomBarIntents()
}