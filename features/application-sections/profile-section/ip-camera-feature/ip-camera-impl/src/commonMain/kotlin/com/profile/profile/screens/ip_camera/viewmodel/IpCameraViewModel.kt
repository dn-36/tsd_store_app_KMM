package com.profile.profile.screens.ip_camera.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.ip_camera.domain.IpCameraUseCase
import com.project.network.Navigation
import com.project.`printer-api`.PrinterScreensApi
import org.koin.mp.KoinPlatform.getKoin

class IpCameraViewModel(
   private val ipCamera:IpCameraUseCase
) : ViewModel() {
   @Composable
   fun IpCameraView(){
       ipCamera.execute("rtsp://192.168.1.150:2000/unicast")
   }

  fun back(){
      val printerScreen:PrinterScreensApi = getKoin().get()
      Navigation.navigator.push(
          printerScreen.printer()
      )
  }

}