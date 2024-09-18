package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain

class PrintOnVkpUseCase(private val repository:InfrastructurePrinterVkpAPI) {
    fun execute(textBarcode: String,
                description: String?,
                heightQRCodeMM:Int,
                fontSize:Float){
        repository.printOnVKP(
        description,
        textBarcode,
        heightQRCodeMM,
        fontSize)
    }
}