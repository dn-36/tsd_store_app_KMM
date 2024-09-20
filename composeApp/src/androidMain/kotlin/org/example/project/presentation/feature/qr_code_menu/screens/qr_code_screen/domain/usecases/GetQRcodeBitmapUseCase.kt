package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.InfrastructurePrinterVkpAPI

class GetQRcodeBitmapUseCase(
    private val printerVkpAPI: InfrastructurePrinterVkpAPI
) {
    fun execute(content:String,heightMM:Int):Bitmap = printerVkpAPI.getQRCodeAndDescription(
        content,heightMM
    )

}