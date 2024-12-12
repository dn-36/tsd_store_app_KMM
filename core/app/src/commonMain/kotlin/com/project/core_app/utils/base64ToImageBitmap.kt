package com.project.core_app.utils

import androidx.compose.ui.graphics.ImageBitmap

expect suspend fun urlImageToImageBitmap(base64String: String): ImageBitmap?