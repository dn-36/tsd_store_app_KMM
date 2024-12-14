package com.project.phone

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.util.Log
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import it.custom.printer.api.android.CustomAndroidAPI
import it.custom.printer.api.android.CustomException
import it.custom.printer.api.android.CustomPrinter
import org.koin.mp.KoinPlatform.getKoin

class VKPPrinter() {



    fun print(
        label: String?,
        barcode: String,
        heightQRCodeMM:Float,
        fontSize:Float,
        context:Context
    ) {
        var prnDevice:CustomPrinter? = null
        try {

            prnDevice =
            CustomAndroidAPI().getPrinterDriverUSB(
                CustomAndroidAPI.EnumUsbDevices(
                   context
                )[0], context
            )

    } catch (e:Exception) {
       Toast.makeText(context,"Не обнаружено usb подключение",Toast.LENGTH_SHORT)
    }

        val image = generateBarcode(barcode, heightQRCodeMM, 1F)
        if (image != null) {
            try {

                prnDevice!!.printImage(
                    image,
                    CustomPrinter.IMAGE_ALIGN_TO_CENTER,
                    CustomPrinter.IMAGE_SCALE_TO_FIT,
                    0
                )

                val textBitmap = textToBitmap(
                    label!!,  fontSize, true
                )

                prnDevice!!.printImage(
                    textBitmap,
                    CustomPrinter.IMAGE_ALIGN_TO_CENTER,
                    CustomPrinter.IMAGE_SCALE_TO_FIT,
                    0
                )

                prnDevice!!.present(100)
            } catch (e: CustomException) {
                Toast.makeText(context,"Error print image: ${e.message}",Toast.LENGTH_SHORT)

            } catch (e: java.lang.Exception) {
                Toast.makeText(context,"Error print image: ${e.message}",Toast.LENGTH_SHORT)
            }
        }
    }

    fun generateBarcode(content: String, heightMm: Float,barWidthMultiplier: Float, format: BarcodeFormat = BarcodeFormat.CODE_128): Bitmap? {
        val content:String = "123456"
       val barCode =  VKPUtils.generateBarcode(/*content*/content, heightMm,barWidthMultiplier,format)

        Log.d("barCode",barCode?.width.toString())
        Log.d("barCode",getBitmapSizeInDp(barCode!!).toString())
      return barCode
    }
    fun textToBitmap(
    text: String,
   // _width: Int,
    fontSize: Float,
    isBold: Boolean
    ):Bitmap{
        return VKPUtils.textToBitmap(
            text,
            180,
            fontSize,
            isBold
        )
    }


    fun getBitmapSizeInDp(bitmap: Bitmap): Pair<Float, Float> {
        val context:Context = getKoin().get()
        val density = context.resources.displayMetrics.density

        // Размеры в пикселях
        val widthInPixels = bitmap.width
        val heightInPixels = bitmap.height

        // Преобразуем пиксели в dp
        val widthInDp = widthInPixels / density
        val heightInDp = heightInPixels / density

        return Pair(widthInDp, heightInDp)
    }
}