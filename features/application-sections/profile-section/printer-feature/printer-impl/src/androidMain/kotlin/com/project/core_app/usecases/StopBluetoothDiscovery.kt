package com.project.core_app.usecases

import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI

class StopBluetoothDiscovery(  private val printerAPI: InfrastructurePrinterTscAPI
) {
    fun execute() {
        printerAPI.stopBluetoothDiscovery()
    }
}