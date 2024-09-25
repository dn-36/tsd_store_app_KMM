package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI


class PrintOnVkpUseCase(private val repository: InfrastructurePrinterVkpAPI) {
    fun execute(textBarcode: String,
                description: String?,
                heightQRCodeMM:Float,
                fontSize:Float){
        repository.printOnVKP(
        description,
        textBarcode,
        heightQRCodeMM,
        fontSize)
    }
}