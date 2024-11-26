package com.profile.profile.screens.ip_camera.domain

import androidx.compose.runtime.Composable

class IpCameraUseCase(
    private val infrastructure:InfrastructureIpCameraApi
) {
          @Composable
       fun execute(url:String){
           infrastructure.RtspStreamPlayer(url)
       }
}