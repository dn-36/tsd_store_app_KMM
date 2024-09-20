package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.Infrastructure

import android.content.Context
import android.graphics.Bitmap
import com.project.printer_barcode.VKPPrinter
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.InfrastructurePrinterVkpAPI

class PrinterVkpImpl(
    private val printer:VKPPrinter,
    private val context: Context,
   // private val utilVKPUtils
    ):
    InfrastructurePrinterVkpAPI {

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

    override fun <T> getQRCodeAndDescription(
        content: String,
        heightMm: Int
    ): T {
        return printer.generateBarcode(
            content,heightMm
        ) as T
    }

    // fun getQRCodeAndDescription() = printer.generateBarcode("jdfbhjfdbv",30)!!

}