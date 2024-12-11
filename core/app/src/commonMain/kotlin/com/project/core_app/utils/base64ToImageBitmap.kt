package com.project.core_app.utils

import androidx.compose.ui.graphics.ImageBitmap

expect suspend fun base64ToImageBitmap(base64String: String): ImageBitmap?