package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.InfrastructurePrinterVkpAPI


class PrintOnVkpUseCase(private val repository: InfrastructurePrinterVkpAPI) {
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