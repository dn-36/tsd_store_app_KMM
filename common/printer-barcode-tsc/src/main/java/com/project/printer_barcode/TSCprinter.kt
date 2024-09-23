package com.project.printer_barcode

import android.graphics.Bitmap
import android.util.Log
import com.example.tscdll.TSCActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object TSCprinter {
    private val tscDll = TSCActivity()
    private var _adressDevice: String = ""
    fun init(adressDevice: String) {
        _adressDevice = adressDevice
        tscDll.openport(adressDevice)
    }

    fun printer(barCode: Bitmap, title: Bitmap, ) {


            val tscDll = TSCActivity()

            //   val settings = viewModel.getCurrentSettings()
            val heightQRcode = 25
            val densty = 4

            val widthTicket = 70
            val heightTicket = 50
            val cefWidthQrCode = 2
            val countChars = ("barcode").chars().count().toInt()


            //   val productName = intent?.getStringExtra(EXTRA_NAME) ?: ""
            //   val barcode = intent?.getStringExtra(EXTRA_BARCODE) ?: ""

            //  vb.tvPrinterStatus.text = "Идет печать..."

            try {

                tscDll.openport(_adressDevice)
                //TscDll.downloadttf("ARIAL.TTF")

                tscDll.sendcommand("SIZE 70 mm, 50 mm\r\n")
                tscDll.sendcommand("GAP 3 mm, 0 mm\r\n")
                tscDll.sendcommand("CODEPAGE UTF-8\r\n")
                tscDll.downloadttf("ARIAL.TTF")
                tscDll.setup(70, 50, 4, 3, 0, 1, 0)
                tscDll.clearbuffer()
               // tscDll.sendbitmap(15, 40, barCode)

                tscDll.barcode(
                    (((widthTicket / 2) - (cefWidthQrCode * countChars * 0.9F)) * densty * 2).toInt(),
                    (heightTicket / 2 - heightQRcode / 1.5F).toInt() * densty * 2,
                    "128",
                    heightQRcode * densty * 2,
                    1,
                    0,
                    cefWidthQrCode,
                    cefWidthQrCode,
                    "barcode"
                )
                tscDll.printlabel(1, 1)
                // tscDll.printlabel(1, 1)
                //val status = tscDll.printerstatus()
                //vb.tvPrinterStatus.text = "Готово [$status]"
                tscDll.closeport(1000)
            } catch (e: Exception) {
                Log.d("errora", e.message.toString())
                //   vb.tvPrinterStatus.text = "Ошибка печати"
                //    showError(e.message)
            }
        }
    }

