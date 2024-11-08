package com.project.core_app.network_base_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.network_base_screen.component.ErrorComponent
import com.project.core_app.network_base_screen.component.LoadingComponent
import kotlin.jvm.Transient

open class NetworkScreen(
    @Transient private val networkComponent: NetworkComponent,
): Screen {


    @Composable
   override fun Content() {

      val networkComponent = remember { networkComponent }
        val state = networkComponent.viewModel.stateNetwork

              networkComponent.Component()
               if(state==StatusNetworkScreen.LOADING) {
                   LoadingComponent()
               }
             if(state==StatusNetworkScreen.ERROR) {
                  ErrorComponent()
              }
           }


    
    
}