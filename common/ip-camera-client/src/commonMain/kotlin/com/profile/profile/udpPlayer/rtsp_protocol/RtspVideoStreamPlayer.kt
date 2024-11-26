package com.profile.profile.udpPlayer.rtsp_protocol

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


expect class  RtspVideoStreamPlayer(){

    fun init(  rtspUrl:String)

    @Composable
    fun Content(
        modifier: Modifier = Modifier
    )

}