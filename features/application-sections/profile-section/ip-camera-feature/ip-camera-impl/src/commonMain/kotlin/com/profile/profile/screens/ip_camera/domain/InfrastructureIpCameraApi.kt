package com.profile.profile.screens.ip_camera.domain

import androidx.compose.runtime.Composable
import io.ktor.http.Url

interface InfrastructureIpCameraApi {

        @Composable
    fun RtspStreamPlayer(url: String)

}