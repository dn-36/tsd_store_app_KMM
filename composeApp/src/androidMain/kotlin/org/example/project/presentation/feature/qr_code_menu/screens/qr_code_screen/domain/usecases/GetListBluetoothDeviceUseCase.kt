package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import android.bluetooth.BluetoothDevice
import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI

class GetListBluetoothDeviceUseCase(private val printerTscAPI: InfrastructurePrinterTscAPI<Bitmap>) {
    fun execute(
        actionAddDevice: (String) -> Unit,
        actionSecuesfull: () -> Unit
    ):List<String> = printerTscAPI.listBluetoothDevice(
    actionAddDevice,
    actionSecuesfull).toSet().toList()
}