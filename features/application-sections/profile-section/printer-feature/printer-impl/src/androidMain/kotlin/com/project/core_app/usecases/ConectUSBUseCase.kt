package com.project.core_app.usecases

import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class ConectUSBUseCase(private val infrastructurePrinterVkp: InfrastructurePrinterVkpAPI) {
    fun execute(){
        infrastructurePrinterVkp.connectUSB()
    }
}