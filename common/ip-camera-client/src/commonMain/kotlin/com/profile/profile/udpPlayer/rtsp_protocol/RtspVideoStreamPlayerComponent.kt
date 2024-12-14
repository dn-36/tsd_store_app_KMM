package com.profile.profile.udpPlayer.rtsp_protocol

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope


expect class  RtspVideoStreamPlayerComponent(){


    @Composable
    fun Content(
        rtspUrl: String,
        modifier: Modifier = Modifier
    )


  suspend fun startRecording(url: String)

  suspend fun startStream(url: String)

  suspend fun stopStream()

  suspend fun  stopRecordStream()


}