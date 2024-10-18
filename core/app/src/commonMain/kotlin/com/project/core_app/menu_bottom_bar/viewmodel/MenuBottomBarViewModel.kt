package com.project.core_app.menu_bottom_bar.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.menu_bottom_bar.ui.Section
import com.project.network.Navigation
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarState


class MenuBottomBarViewModel : ViewModel() {

    var menuBottomBarState by mutableStateOf(MenuBottomBarState())

    fun processIntent(intent: MenuBottomBarIntents){
        when(intent){
            is MenuBottomBarIntents.CRM -> {crm(intent.screen)}
            is MenuBottomBarIntents.Profile -> {profile(intent.screen)}
            is MenuBottomBarIntents.Chats -> {chats(intent.screen)}
            is MenuBottomBarIntents.Tape -> {tape(intent.screen)}
            is MenuBottomBarIntents.Home -> {organizations(intent.screen)}
            is MenuBottomBarIntents.SetScreen -> {setScreen(intent.section)}
        }
    }

   private fun setScreen(section:Section ){
        menuBottomBarState = menuBottomBarState.copy(
       when(section){
           Section.CHATS -> listOf(
               Color.Transparent,
               Color.Transparent,
               Color.Transparent,
               Color(0xFFFF9800),
               Color.Transparent
           )
           Section.CRM  -> listOf(
               Color.Transparent,
               Color(0xFFFF9800),
               Color.Transparent,
               Color.Transparent,
               Color.Transparent
           )
           Section.PROFILE  -> listOf(
               Color.Transparent,
               Color.Transparent,
               Color.Transparent,
               Color.Transparent,
               Color(0xFFFF9800)
           )
           Section.ORGANIZATION  -> listOf(
               Color(0xFFFF9800),
               Color.Transparent,
               Color.Transparent,
               Color.Transparent,
               Color.Transparent,
           )
           Section.TAPE -> listOf(
               Color.Transparent,
               Color.Transparent,
               Color(0xFFFF9800),
               Color.Transparent,
               Color.Transparent
           )
       }
        )
    }


   private fun crm(menuScreen:Screen) {
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
}