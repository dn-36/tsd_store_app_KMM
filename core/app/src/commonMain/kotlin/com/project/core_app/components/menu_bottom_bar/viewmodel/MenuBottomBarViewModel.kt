package com.project.core_app.components.menu_bottom_bar.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.project.network.Navigation
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarState


class MenuBottomBarViewModel : ViewModel() {

    var menuBottomBarState by mutableStateOf(MenuBottomBarState(MenuBottomBarSection.ORGANIZATION))
    private var setUsed:Boolean = false
    fun processIntent(intent: MenuBottomBarIntents){
        when(intent){

            is MenuBottomBarIntents.CRM -> {crm(intent.screen)}
            is MenuBottomBarIntents.Profile -> {profile(intent.screen)}
            is MenuBottomBarIntents.Chats -> {chats(intent.screen)}
            is MenuBottomBarIntents.Tape -> {tape(intent.screen)}
            is MenuBottomBarIntents.Home -> {organizations(intent.screen)}
            is MenuBottomBarIntents.SetScreen -> {
                if(!setUsed) {
                    true
                    setScreen(intent.section)
                }
            }
        }
    }
    fun crm(menuScreen:Screen) {

        Navigation.navigator.push(menuScreen)

    }

    fun profile(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    fun organizations(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    fun chats(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    fun tape(screen: Screen) {

        Navigation.navigator.push(screen)

    }
    fun setScreen(section: MenuBottomBarSection){

        menuBottomBarState  = menuBottomBarState.copy(section)

    }
}