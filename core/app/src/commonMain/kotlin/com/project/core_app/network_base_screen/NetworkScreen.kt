package com.project.core_app.network_base_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.network_base_screen.component.ErrorComponent
import com.project.core_app.network_base_screen.component.LoadingComponent

open class NetworkScreen(
   private val networkComponent: NetworkComponent,
): Screen {


    @Composable
   override fun Content() {
        val state = networkComponent.networkViewModel.stateNetwork

              networkComponent.Component()
               if(state==StatusNetworkScreen.LOADING) {
                   LoadingComponent()
               }
             if(state==StatusNetworkScreen.ERROR) {
                  ErrorComponent()
              }
           }


    
    
}