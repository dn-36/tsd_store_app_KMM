package com.profile.profile.screens.ip_camera.repository

import androidx.compose.runtime.Composable
import com.profile.profile.screens.ip_camera.domain.InfrastructureIpCameraApi
import com.profile.profile.udpPlayer.rtsp_protocol.RtspVideoStreamPlayerComponent

class InfrastructureIpCameraImpl(
    private val apiCamera : RtspVideoStreamPlayerComponent
) : InfrastructureIpCameraApi {

    @Composable
    override fun RtspStreamPlayer(url:String) {
        apiCamera.Content(url)
    }

    override suspend fun startRecordStream(url: String) {
        apiCamera.startRecording(url)
    }

    override suspend fun startStream(url: String) {
       apiCamera.startStream(url)
    }

    override suspend fun stopStream() {
      apiCamera.stopStream()
    }

    override suspend fun stopRecordStream() {
      apiCamera.stopRecordStream()
    }


}