package com.project.core_app.utils

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes


actual suspend fun base64ToImageBitmap(base64String: String): ImageBitmap? {

    val client = HttpClient(CIO)

    return try {
        // Загружаем байты изображения с сервера
        val byteArray: ByteArray = client.get("https://delta.online/storage/${base64String}").readBytes()

        // Преобразуем байты в Bitmap
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        // Преобразуем Bitmap в ImageBitmap
        bitmap?.asImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        client.close()
    }

}