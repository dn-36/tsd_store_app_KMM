package com.profile.profile.screens.ip_camera.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.ip_camera.domain.IpCameraUseCase
import com.profile.profile.screens.settings.SettingsConnectIpCameraScreen
import com.project.network.Navigation
import com.project.`printer-api`.PrinterScreensApi
import org.koin.mp.KoinPlatform.getKoin

class IpCameraViewModel(
   private val ipCamera:IpCameraUseCase
) : ViewModel() {
    private var isSetedScreen = false
   @Composable
   fun IpCameraView(){
       if(!isSetedScreen) {
           isSetedScreen = true
           ipCamera.execute("rtsp://192.168.1.150:2000/unicast")
       }
   }

  fun back(){

      Navigation.navigator.pop()
  }
  fun goToSetting(){

      Navigation.navigator.replaceAll(
          SettingsConnectIpCameraScreen()
      )
  }

}