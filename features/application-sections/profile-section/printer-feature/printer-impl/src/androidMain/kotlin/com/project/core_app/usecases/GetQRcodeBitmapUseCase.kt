package com.project.core_app.usecases

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class GetQRcodeBitmapUseCase(
    private val printerVkpAPI: InfrastructurePrinterVkpAPI
) {
    fun execute(content:String,heightMM:Float):Bitmap = printerVkpAPI.getQRCode(
        content,heightMM
    )

}