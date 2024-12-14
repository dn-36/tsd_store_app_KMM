package com.profile.profile.screens.ip_camera.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class IpCameraIntent(){
    data class IpCameraView(val scope: CoroutineScope):IpCameraIntent()
    data class StartRecording(val scope: CoroutineScope):IpCameraIntent()
    data class StopRecording(val scope: CoroutineScope):IpCameraIntent()
}
