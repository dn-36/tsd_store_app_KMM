package com.profile.profile.screens.ip_camera.domain

import kotlinx.coroutines.CoroutineScope

class StopStreamUseCase (private val infrastructure:InfrastructureIpCameraApi) {

  suspend  fun execute() {
        infrastructure.stopStream()
    }
}