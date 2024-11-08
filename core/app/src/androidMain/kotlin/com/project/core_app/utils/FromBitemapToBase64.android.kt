package com.project.core_app.utils

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import io.ktor.util.encodeBase64
import java.io.ByteArrayOutputStream


actual fun ImageBitmap.encodeToByteArray(): ByteArray {
    val bitmap: Bitmap = this.asAndroidBitmap()
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream.toByteArray()
}