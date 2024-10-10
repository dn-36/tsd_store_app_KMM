package com.project.core_app.usecases

import kotlinx.coroutines.delay
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI

class GetListBluetoothDeviceUseCase(private val printerTscAPI: InfrastructurePrinterTscAPI) {
   suspend fun execute(
        actionAddDevice: (String) -> Unit,
        actionSecuesfull: () -> Unit
    ):List<String> {
     val list = printerTscAPI.listBluetoothDevice(
           actionAddDevice,
           actionSecuesfull).toSet().toList()

       delay(10_000)
       printerTscAPI.stopBluetoothDiscovery()
       actionSecuesfull()
       return list

   }


}