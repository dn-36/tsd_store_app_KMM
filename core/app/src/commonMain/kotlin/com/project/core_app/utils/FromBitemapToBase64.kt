package com.project.core_app.utils

import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.BytePacketBuilder
import io.ktor.utils.io.core.build
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully

fun imageBitmapToBase64(imageBitmap: ImageBitmap): String {
    val packetBuilder = BytePacketBuilder()
    val byteArray = imageBitmap.encodeToByteArray()
    packetBuilder.writeFully(byteArray)
    val resultByteArray = packetBuilder.build().readBytes()
    return resultByteArray.encodeBase64()
}

expect fun ImageBitmap.encodeToByteArray(): ByteArray