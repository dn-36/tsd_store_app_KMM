package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.Infrastructure

import android.content.Context
import com.project.printer_barcode.VKPPrinter
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain.InfrastructurePrinterVkpAPI

class InfrastructurePrinterVkpImpl(private val printer:VKPPrinter,private val context: Context): InfrastructurePrinterVkpAPI {

    override fun printOnVKP(description: String?,
                            textBarcode: String,
                            heightQRCodeMM:Int,
                            fontSize:Float){
    printer.print(
        description,
        textBarcode,
        heightQRCodeMM,
        fontSize,
        context
    )
    }

    override fun connectUSB() {

    }
}