package com.project.core_app.usecases

import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI

class ConnectToBleutoothDeviceUseCase ( private val printerTscAPI: InfrastructurePrinterTscAPI) {
    suspend fun execute(
        device: String,
        actionSuccessfully: () -> Unit,
        actionError: () -> Unit,
    ) = printerTscAPI.connectToDevice(
        device,
        actionSuccessfully,
        actionError)
}

