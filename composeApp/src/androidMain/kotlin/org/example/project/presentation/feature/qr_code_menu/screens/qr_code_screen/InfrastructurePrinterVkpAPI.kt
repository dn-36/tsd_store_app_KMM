package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen


interface InfrastructurePrinterVkpAPI {
    fun printOnVKP(description: String?,
                   textBarcode: String,
                   heightQRCodeMM:Int,
                   fontSize:Float)
    fun connectUSB()
    fun <T>getQRCode(content: String,
                     heightMm: Int
    ):T

    fun <T>getTitleProduct(
        content: String,
        fontSize: Int
    ):T

}