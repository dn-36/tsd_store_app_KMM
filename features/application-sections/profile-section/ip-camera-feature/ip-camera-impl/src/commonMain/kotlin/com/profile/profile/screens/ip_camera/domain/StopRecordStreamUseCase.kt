package com.profile.profile.screens.ip_camera.domain

import kotlinx.coroutines.CoroutineScope

class StopRecordStreamUseCase(private val infrastructure:InfrastructureIpCameraApi) {

  suspend  fun execute() {
        infrastructure.stopRecordStream()
    }
}