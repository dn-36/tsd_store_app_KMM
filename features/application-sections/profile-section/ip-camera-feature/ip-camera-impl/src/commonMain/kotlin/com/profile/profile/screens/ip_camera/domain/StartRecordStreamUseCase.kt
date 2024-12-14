package com.profile.profile.screens.ip_camera.domain

import kotlinx.coroutines.CoroutineScope

class StartRecordStreamUseCase(private val infrastructure:InfrastructureIpCameraApi) {

   suspend fun execute(url:String,){
        infrastructure.startRecordStream(url)
    }
}