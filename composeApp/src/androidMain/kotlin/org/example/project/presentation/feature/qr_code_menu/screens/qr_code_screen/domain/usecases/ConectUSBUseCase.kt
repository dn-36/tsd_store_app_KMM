package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class ConectUSBUseCase(private val infrastructurePrinterVkp: InfrastructurePrinterVkpAPI) {
    fun execute(){
        infrastructurePrinterVkp.connectUSB()
    }
}