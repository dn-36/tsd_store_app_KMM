package com.profile.profile.udpPlayer.rtsp_protocol

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

actual class RtspVideoStreamPlayerComponent actual constructor() {

    @Composable
    actual fun Content(rtspUrl: String, modifier: Modifier) {
    }

    actual suspend fun startRecording(url: String) {
    }

    actual suspend fun startStream(url: String) {
    }

    actual suspend fun stopStream() {
    }

    actual suspend fun stopRecordStream() {
    }


}