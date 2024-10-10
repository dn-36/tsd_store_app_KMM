package com.project.core_app.usecases

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class GetTitleProductBiteMapUseCase(
    private val printerAPI: InfrastructurePrinterVkpAPI
) {
    fun execute(content:String,fontSize:Float):Bitmap =
        printerAPI.getTitleProduct(content,fontSize)
}