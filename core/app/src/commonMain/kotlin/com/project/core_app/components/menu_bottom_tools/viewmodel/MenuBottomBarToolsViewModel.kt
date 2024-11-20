package com.project.core_app.components.menu_bottom_tools.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.chats.IPCamearaScreensApi
import com.project.network.Navigation
import com.project.`printer-api`.PrinterScreensApi
import org.koin.mp.KoinPlatform.getKoin

class MenuBottomBarToolsViewModel : ViewModel() {

    var state by mutableStateOf(MenuBottomBarToolsState(MenuBottomBarToolsSection.PRINT))

    private var setUsed:Boolean = false

    fun processIntent(intent: MenuBottomBarToolsIntents){
        when(intent){
            //is MenuBottomBarWarehouseIntents.Finance -> { finance(intent.screen) }

            is MenuBottomBarToolsIntents.Print -> { print(/*intent.screen*/) }

            is MenuBottomBarToolsIntents.Tools -> { camera(/*intent.screen*/) }

        //    is com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarWarehouseIntents.MenuBottomBarToolsIntents.Profile -> { profile(intent.screen) }

            is  MenuBottomBarToolsIntents.SetScreen -> {
                if(!setUsed) {
                setUsed = true
                setScreen( intent.section ) } }
        }
    }

    fun camera() {
        val screens:IPCamearaScreensApi = getKoin().get()
        state = state.copy(section = MenuBottomBarToolsSection.Camera)
    //MenuBottomBarToolsSection.WAREHOUSE)
          Navigation.navigator.push(
       screens.ipCamera()
        )
    }
/*
    fun finance(screen: Screen) {
        Navigation.navigator.push(screen)
    }*/

    fun print() {
        val screens:PrinterScreensApi = getKoin().get()
        Navigation.navigator.push(
            screens.printer()
        )
        state = state.copy(section = MenuBottomBarToolsSection.PRINT )
 Navigation.navigator.push(screens.printer())

}
/*
fun profile(screen:Screen) {

  Navigation.navigator.push(screen)

}*/

fun setScreen(section: MenuBottomBarToolsSection){

  state  = state.copy(section)

}

}