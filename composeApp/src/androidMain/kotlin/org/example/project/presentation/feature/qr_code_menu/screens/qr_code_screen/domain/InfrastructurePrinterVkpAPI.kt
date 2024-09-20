package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain


interface InfrastructurePrinterVkpAPI {
    fun printOnVKP(description: String?,
                   textBarcode: String,
                   heightQRCodeMM:Int,
                   fontSize:Float)
    fun connectUSB()
    fun <T>getQRCodeAndDescription(content: String,
                                   heightMm: Int
    ):T
}