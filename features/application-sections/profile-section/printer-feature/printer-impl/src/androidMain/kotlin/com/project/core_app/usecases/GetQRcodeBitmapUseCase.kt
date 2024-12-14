package com.project.core_app.usecases

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components.TypeQrCode

class GetQRcodeBitmapUseCase(
    private val printerVkpAPI: InfrastructurePrinterVkpAPI
) {
    fun execute(
        content:String,
        heightMM:Float,
        barWidthMultiplier: Float,
        typeQrCode: TypeQrCode
    ):Bitmap = printerVkpAPI.getQRCode(
        content,heightMM,barWidthMultiplier,typeQrCode
    )

}