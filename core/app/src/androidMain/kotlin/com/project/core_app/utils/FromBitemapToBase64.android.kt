package com.project.core_app.utils

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.util.encodeBase64
import java.io.ByteArrayOutputStream






actual fun ImageBitmap.encodeToByteArray(compressionQuality: Int): ByteArray {
    val bitmap: Bitmap = this.asAndroidBitmap()
    val outputStream = ByteArrayOutputStream()

    // Compress the bitmap with the given quality
    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, outputStream)
    return outputStream.toByteArray()
}