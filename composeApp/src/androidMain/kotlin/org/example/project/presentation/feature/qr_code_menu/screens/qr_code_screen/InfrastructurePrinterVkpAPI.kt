package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen


interface InfrastructurePrinterVkpAPI {
    fun printOnVKP(description: String?,
                   textBarcode: String,
                   heightQRCodeMM:Float,
                   fontSize:Float)
    fun connectUSB()
    fun <T>getQRCode(content: String,
                     heightMm: Float
    ):T

    fun <T>getTitleProduct(
        content: String,
        fontSize: Float
    ):T

}