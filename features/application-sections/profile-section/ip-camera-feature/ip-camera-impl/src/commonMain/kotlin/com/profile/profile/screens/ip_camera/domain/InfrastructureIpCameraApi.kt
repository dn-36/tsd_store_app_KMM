package com.profile.profile.screens.ip_camera.domain

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

interface InfrastructureIpCameraApi {

    @Composable
    fun RtspStreamPlayer(url: String)
   suspend fun startRecordStream(url: String)
   suspend fun startStream(url: String)
   suspend fun stopStream()
   suspend fun stopRecordStream()

}