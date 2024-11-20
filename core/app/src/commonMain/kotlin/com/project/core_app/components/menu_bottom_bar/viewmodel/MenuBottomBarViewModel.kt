package com.project.core_app.components.menu_bottom_bar.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.components.menu_bottom_bar.domian.GetCountNewMessageUseCase
import com.project.core_app.components.menu_bottom_bar.domian.MenuBottomBarRepositoryApi
import com.project.network.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarState


class MenuBottomBarViewModel(
    private val getCountNewMessageUseCase: GetCountNewMessageUseCase
) : ViewModel() {
    private companion object{
     var countNewMessge:Int = 0
    }
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
                    setScreen(intent.section,intent.scope)
            }
        }
    }

    private fun crm(menuScreen:Screen) {

        Navigation.navigator.push(menuScreen)

    }

    private fun profile(screen:Screen) {

        Navigation.navigator.push(screen)

    }

    private fun organizations(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    private fun chats(screen: Screen) {

        Navigation.navigator.push(screen)

    }

    private fun tape(screen: Screen) {

        Navigation.navigator.push(screen)

    }
   private fun setScreen(section: MenuBottomBarSection,scope: CoroutineScope){
       if(!setUsed) {
       true
       menuBottomBarState  = menuBottomBarState.copy(section, countNewMessge)

           scope.launch(Dispatchers.IO) {
               while (isActive) {
                   countNewMessge = getCountNewMessageUseCase.execute()
                   menuBottomBarState = menuBottomBarState.copy(countNewMessage = countNewMessge)
                   delay(2000L)
               }
           }
       }
    }
}