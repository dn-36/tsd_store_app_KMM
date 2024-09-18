package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain

class ConectUSBUseCase(private val infrastructurePrinterVkp: InfrastructurePrinterVkpAPI ) {
    fun execute(){
        infrastructurePrinterVkp.connectUSB()
    }
}