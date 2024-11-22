package com.project.core_app.components.menu_bottom_bar_contragents.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsIntents
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsSection
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsState
import com.project.network.Navigation

class MenuBottomBarContragentsViewModel : ViewModel() {

    var state by mutableStateOf(MenuBottomBarContragentsState( MenuBottomBarContragentsSection.CONTRAGENTS ))

    private var setUsed:Boolean = false

    fun processIntent(intent: MenuBottomBarContragentsIntents){

        when(intent){

            is MenuBottomBarContragentsIntents.Contragents -> contragents(intent.screen)

            is MenuBottomBarContragentsIntents.Locations -> locations(intent.screen)

            is MenuBottomBarContragentsIntents.SetScreen -> { if(!setUsed) {

                setUsed = true

                setScreen( intent.section ) } }
        }
    }

    fun contragents(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    fun locations(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    fun setScreen(section: MenuBottomBarContragentsSection){

        state  = state.copy(section)

    }

}