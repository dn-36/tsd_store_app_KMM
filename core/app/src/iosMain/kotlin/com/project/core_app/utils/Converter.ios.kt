package com.project.core_app.utils

import androidx.compose.ui.graphics.ImageBitmap
import platform.Foundation.NSData
import platform.Foundation.create
import platform.UIKit.UIImage



actual fun base64ToImageBitmap(encodedImage: String): ImageBitmap? {
    return try {
        val data = NSData.create(encodedImage, NSDataBase64DecodingOptions)
        val uiImage = UIImage(data = data)
        uiImage?.asImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}