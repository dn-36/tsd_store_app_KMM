package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`Infrastructure-impl`

import android.graphics.Bitmap
import com.project.phone.TSCprinter
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI
import org.koin.mp.KoinPlatform.getKoin


class PrinterTscImpl(private val printer:TSCprinter):InfrastructurePrinterTscAPI {

    override fun listBluetoothDevice(  actionAddDevice: (String) -> Unit,
                                       actionSecuesfull: () -> Unit) : List<String> {

        val list =  printer.searchForDevices(actionAddDevice)
            .map { it.name?:it.address }.toSet().toList()
        return list

    }

    override fun  <T> print(
        barCode: T,
        title: T,
        heightTicket:Int,
        widthTicket:Int,
        xQrCode:Int,
        yQrCode:Int
    ) {
       printer.print(
           barCode as Bitmap,
           title as Bitmap,
           heightTicket,
           widthTicket,
           xQrCode,
           yQrCode
       )
    }

    override suspend fun connectToDevice(device: String,
                                         actionSuccessfully: () -> Unit,
                                         actionError: () -> Unit,) {
                 printer.connectToDevice(
                     device,
                     getKoin().get(),
                     actionSuccessfully,
                     actionError
                 )
    }


    override fun stopBluetoothDiscovery() {
        printer.stopBluetoothDiscovery()
    }

}