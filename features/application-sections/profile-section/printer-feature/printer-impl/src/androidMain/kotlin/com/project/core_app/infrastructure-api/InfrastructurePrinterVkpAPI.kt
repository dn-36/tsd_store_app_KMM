package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`

import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components.TypeQrCode


interface InfrastructurePrinterVkpAPI {
    fun printOnVKP(description: String?,
                   textBarcode: String,
                   heightQRCodeMM:Float,
                   fontSize:Float,)
    fun connectUSB()
    fun <T>getQRCode(content: String,
                     heightMm: Float,
                     barWidthMultiplier: Float,
                     typeQrCode: TypeQrCode
    ):T

    fun <T>getTitleProduct(
        content: String,
        fontSize: Float,

    ):T


}