package com.profile.profile.udpPlayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope


expect class StreamUdpPlayer(
    multicastAddress: String,
    multicastPort: Int,
    packetSize: Int
) {

    fun startVideoStream(scope: CoroutineScope)


         @Composable
     fun VideoStream(modifier: Modifier)
}