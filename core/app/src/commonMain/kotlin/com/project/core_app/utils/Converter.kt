package com.project.core_app.utils


import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.BytePacketBuilder
import io.ktor.utils.io.core.build
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully
import kotlin.math.sqrt




expect fun ImageBitmap.encodeToByteArray(compressionQuality: Int = 100): ByteArray

fun imageBitmapToBase64(imageBitmap: ImageBitmap): String {
    val maxSizeInBytes = 5 * 1024 * 1024 // 5 MB
    var quality = 50
    var byteArray = imageBitmap.encodeToByteArray(quality)

    // Compress the image if it exceeds the size limit
    while (byteArray.size > maxSizeInBytes && quality > 10) {
        quality -= 10
        byteArray = imageBitmap.encodeToByteArray(quality)
    }

    // Encode to Base64
    val packetBuilder = BytePacketBuilder()
    packetBuilder.writeFully(byteArray)
    val resultByteArray = packetBuilder.build().readBytes()
    return resultByteArray.encodeBase64()
}



