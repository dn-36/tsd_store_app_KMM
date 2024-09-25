package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`Infrastructure-impl`

import android.bluetooth.BluetoothDevice
import android.graphics.Bitmap
import com.project.printer_barcode.TSCprinter
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI
import org.koin.mp.KoinPlatform.getKoin
import tsdstorekmm.composeapp.generated.resources.Res


class PrinterTscImpl(private val printer:TSCprinter):InfrastructurePrinterTscAPI<Bitmap> {

    override fun listBluetoothDevice(  actionAddDevice: (String) -> Unit,
                                       actionSecuesfull: () -> Unit) : List<String> {

        return printer.searchForDevices(actionAddDevice,actionSecuesfull).map { it.name?:it.address }.toSet().toList()

    }

    override fun print(barCode: Bitmap, title: Bitmap) {
       printer.print(barCode, title)
    }

    override fun connectToDevice(device: String) {
                 printer.connectToDevice(device,getKoin().get())
    }

    override fun cleanup() {
        printer.cleanup()
    }


    //override var bluetoothDevice: BluetoothDevice? = null

}