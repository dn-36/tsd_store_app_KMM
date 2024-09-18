package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain

interface InfrastructurePrinterVkpAPI {
    fun printOnVKP(description: String?,
                   textBarcode: String,
                   heightQRCodeMM:Int,
                   fontSize:Float)
    fun connectUSB()
}