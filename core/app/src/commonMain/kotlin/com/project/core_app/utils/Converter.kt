package com.project.core_app.utils


import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.BytePacketBuilder
import io.ktor.utils.io.core.build
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully



expect fun ImageBitmap.encodeToByteArray(): ByteArray

fun imageBitmapToBase64(imageBitmap: ImageBitmap): String {
    val packetBuilder = BytePacketBuilder()
    val byteArray = imageBitmap.encodeToByteArray()
    packetBuilder.writeFully(byteArray)
    val resultByteArray = packetBuilder.build().readBytes()
    println(":::::::::{{{{}}}}::::::::::::::")
    println(resultByteArray.encodeBase64())
    return resultByteArray.encodeBase64()
}

/*
fun converterBase64ToImageBitmap(base64String: String): ImageBitmap? {
    // Декодируем строку Base64 в ByteString
    val byteString = base64String.decodeBase64() ?: return null
    // Преобразуем ByteString в ByteArray
    val imageBytes = byteString.toByteArray()
    // Создаем изображение Skia из байтов
    val skiaImage = Image.makeFromEncoded(imageBytes)
    // Преобразуем Skia Image в ImageBitmap
    return skiaImage.toComposeImageBitmap ()!!//.asImageBitmap()
}*/

