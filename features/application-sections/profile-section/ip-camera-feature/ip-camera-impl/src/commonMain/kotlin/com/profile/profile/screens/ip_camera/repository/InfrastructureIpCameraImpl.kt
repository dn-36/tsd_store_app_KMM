package com.profile.profile.screens.ip_camera.repository

import androidx.compose.runtime.Composable
import com.profile.profile.screens.ip_camera.IpCameraScreen
import com.profile.profile.screens.ip_camera.domain.InfrastructureIpCameraApi
import com.profile.profile.udpPlayer.rtsp_protocol.RtspVideoStreamPlayer

class InfrastructureIpCameraImpl(

    private val apiCamera : RtspVideoStreamPlayer
) : InfrastructureIpCameraApi {
    init {
        apiCamera.init("rtsp://192.168.1.150:2000/unicast")
    }
    @Composable
    override fun RtspStreamPlayer(url:String) {
        apiCamera.Content()
    }
}