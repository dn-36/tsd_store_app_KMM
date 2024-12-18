package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`


interface InfrastructurePrinterTscAPI {
    fun listBluetoothDevice( actionAddDevice: (String) -> Unit,
                             actionSecuesfull: () -> Unit):List<String>


  suspend  fun connectToDevice(device: String,
                               actionSuccessfully: () -> Unit,
                               actionError: () -> Unit,)

    fun <Bitmap> print(
        barCode: Bitmap,
        title: Bitmap,
        heightTicket:Int,
        widthTicket:Int,
        xQrCode:Int,
        yQrCode:Int
        )

    fun stopBluetoothDiscovery()

}
