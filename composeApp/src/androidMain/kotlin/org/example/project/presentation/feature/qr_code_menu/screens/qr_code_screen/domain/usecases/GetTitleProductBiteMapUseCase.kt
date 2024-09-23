package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.InfrastructurePrinterVkpAPI

class GetTitleProductBiteMapUseCase(
    private val printerVkpAPI: InfrastructurePrinterVkpAPI
) {
    fun execute(content:String,fontSize:Int):Bitmap =
        printerVkpAPI.getTitleProduct(content,fontSize)
}