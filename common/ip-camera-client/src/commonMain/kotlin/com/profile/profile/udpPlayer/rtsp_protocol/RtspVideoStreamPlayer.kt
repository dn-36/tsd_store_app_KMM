package com.profile.profile.udpPlayer.rtsp_protocol

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


expect class  RtspVideoStreamPlayer(){
    @Composable
    fun Content(
        rtspUrl:String,
        modifier: Modifier = Modifier)

}