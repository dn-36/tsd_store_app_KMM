package com.project.core_app.menu_bottom_bar.viewmodel

import cafe.adriel.voyager.core.screen.Screen
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection

sealed class MenuBottomBarIntents {
    data class CRM(val screen: Screen): MenuBottomBarIntents()
    data class Profile(val screen: Screen): MenuBottomBarIntents()
    data class Home(val screen: Screen): MenuBottomBarIntents()
    data class Chats(val screen: Screen): MenuBottomBarIntents()
    data class Tape(val screen: Screen): MenuBottomBarIntents()
    data class SetScreen(val section: MenuBottomBarSection): MenuBottomBarIntents()
}