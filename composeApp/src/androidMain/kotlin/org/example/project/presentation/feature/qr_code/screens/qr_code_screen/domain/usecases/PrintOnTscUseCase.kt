package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases

import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI

class  PrintOnTscUseCase<Bitmap>(private val infrastructurePrinterTscAPI: InfrastructurePrinterTscAPI) {

    fun execute(
        barcode:Bitmap,
        title:Bitmap,
        heightTicket:Int,
        widthTicket:Int,
        xQrCode:Int,
        yQrCode:Int
    ){
        infrastructurePrinterTscAPI.print(
            barcode,
            title,
            heightTicket,
            widthTicket,
            xQrCode,
            yQrCode)
    }

}