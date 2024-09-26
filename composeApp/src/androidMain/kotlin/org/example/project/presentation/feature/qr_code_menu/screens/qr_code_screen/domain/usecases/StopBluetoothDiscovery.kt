package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class StopBluetoothDiscovery(  private val printerAPI: InfrastructurePrinterTscAPI
) {
    fun execute() {
        printerAPI.stopBluetoothDiscovery()
    }
}