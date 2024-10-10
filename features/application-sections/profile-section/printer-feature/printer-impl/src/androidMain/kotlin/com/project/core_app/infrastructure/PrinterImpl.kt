package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.infrastructure

import android.content.Context
import com.project.phone.VKPPrinter
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class PrinterImpl(
    private val printer:VKPPrinter,
    private val context: Context,
   // private val utilVKPUtils
    ):
    InfrastructurePrinterVkpAPI {

    override fun printOnVKP(description: String?,
                            textBarcode: String,
                            heightQRCodeMM:Float,
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

    override fun <T> getQRCode(content: String, heightMm: Float): T {
        return printer.generateBarcode(
            content,heightMm
        ) as T
    }

    override fun <T> getTitleProduct(content: String, fontSize: Float): T {
        return printer.textToBitmap(
            content,fontSize.toFloat(),true
        ) as T
    }



    // fun getQRCodeAndDescription() = printer.generateBarcode("jdfbhjfdbv",30)!!

}