package com.project.core_app.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.io.bytestring.toByteString
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.posix.memcpy


@OptIn(ExperimentalForeignApi::class)
actual fun ImageBitmap.encodeToByteArray(): ByteArray {
    val uiImage: UIImage = this.toUIImage()
    val imageData: NSData? = UIImagePNGRepresentation(uiImage)
    val byteArray = ByteArray(imageData!!.length.toInt())
    memcpy(byteArray.refTo(0), imageData!!.bytes, imageData!!.length)
    return byteArray
}