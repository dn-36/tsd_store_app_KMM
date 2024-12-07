package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`Infrastructure-impl`

import android.content.Context
import com.project.phone.VKPPrinter
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.`infrastructure-api`.InfrastructurePrinterVkpAPI

class PrinterVkpImpl(
    private val printer: VKPPrinter,
    private val context: Context,
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

    override fun <T> getQRCode(content: String, heightMm: Float,barWidthMultiplier: Float): T {
        return printer.generateBarcode(
            content,heightMm,barWidthMultiplier
        ) as T
    }

    override fun <T> getTitleProduct(content: String, fontSize: Float): T {
        return printer.textToBitmap(
            content,fontSize.toFloat(),true
        ) as T
    }



    // fun getQRCodeAndDescription() = printer.generateBarcode("jdfbhjfdbv",30)!!

}