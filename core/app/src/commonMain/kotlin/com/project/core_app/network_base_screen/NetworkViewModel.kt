package com.project.core_app.network_base_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class NetworkViewModel:ViewModel(){
   var stateNetwork by mutableStateOf(StatusNetworkScreen.LOADING)
   private set

   fun setStatus(status:StatusNetworkScreen){
       stateNetwork = status
   }

}

 enum class StatusNetworkScreen(){
    SECCUESS,LOADING,ERROR
 }

