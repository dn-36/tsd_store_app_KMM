package com.profile.profile.screens.ip_camera.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.project.core_app.utils.Stopwatch

data class IpCameraState(
    val stopwatch: String = "",
    var isRecorded: Boolean = false
)