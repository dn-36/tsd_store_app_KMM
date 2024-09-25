package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases

import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterTscAPI

class  PrintOnTscUseCase<Bitmap>(private val infrastructurePrinterTscAPI: InfrastructurePrinterTscAPI<Bitmap>) {

    fun execute(barcode:Bitmap,title:Bitmap){
        infrastructurePrinterTscAPI.print(barcode,title)
    }

}