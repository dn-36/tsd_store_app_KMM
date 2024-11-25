package com.profile.profile.udpPlayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Bitmap
import platform.Foundation.NSData
import platform.Foundation.create
import platform.UIKit.UIImage



actual class StreamUdpPlayer actual constructor(
    multicastAddress: String,
    multicastPort: Int,
    packetSize: Int
) {

    actual fun startVideoStream(scope: CoroutineScope) {
        // Реализация для iOS
        // Можно использовать библиотеку CocoaAsyncSocket для обработки UDP
    }


    @Composable
    actual fun VideoStream(modifier: Modifier) {
        // Реализация для отображения данных на iOS
        // Используйте SwiftUI или другую библиотеку
    }

    actual fun stopVideoStream() {
    }
}