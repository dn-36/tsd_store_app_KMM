package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`


interface InfrastructurePrinterTscAPI <Bitmap> {
    fun listBluetoothDevice( actionAddDevice: (String) -> Unit,
                             actionSecuesfull: () -> Unit):List<String>


    fun connectToDevice(device: String)

    fun print(barCode: Bitmap, title: Bitmap,)

    fun cleanup()

}
