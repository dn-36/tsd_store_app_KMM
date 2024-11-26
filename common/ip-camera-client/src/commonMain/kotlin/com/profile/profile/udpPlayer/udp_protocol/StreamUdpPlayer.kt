package com.profile.profile.udpPlayer.udp_protocol

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope

expect class StreamUdpPlayer(
    multicastAddress: String,
    multicastPort: Int,
    packetSize: Int
) {
    fun startVideoStream(scope: CoroutineScope)

    fun stopVideoStream()
         @Composable
     fun VideoStream(modifier: Modifier)

}