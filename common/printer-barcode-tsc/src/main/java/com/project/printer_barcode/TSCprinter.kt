package com.project.printer_barcode

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.tscdll.TSCActivity

object TSCprinter {
    private val tscDll = TSCActivity()

      private  var _device: BluetoothDevice? = null



    fun init(device: BluetoothDevice) {
        _device = device
        //tscDll.openport(_device!!.address)
    }

    fun printer(barCode: Bitmap, title: Bitmap, ) {




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

                tscDll.openport(
                    _device!!.address
                )
                //TscDll.downloadttf("ARIAL.TTF")

                tscDll.sendcommand("SIZE 70 mm, 50 mm\r\n")
                tscDll.sendcommand("GAP 3 mm, 0 mm\r\n")
                tscDll.sendcommand("CODEPAGE UTF-8\r\n")
                tscDll.downloadttf("ARIAL.TTF")
                tscDll.setup(70, 50, 4, 3, 0, 1, 0)
                tscDll.clearbuffer()

               // tscDll.sen

                tscDll.barcode(
                    (((widthTicket / 2) - (cefWidthQrCode * countChars * 0.9F)) * densty * 2).toInt(),
                    (heightTicket / 2 - heightQRcode / 1.5F).toInt() * densty * 2,
                    "128",
                    heightQRcode * densty * 2,
                    1,
                    0,
                    cefWidthQrCode,
                    cefWidthQrCode,
                    "tsd store"
                )
              //  tscDll.sendbitmap(40,0,title,)
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

    fun getPairedDevices(context: Context): List<BluetoothDevice> {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        val deviceList = mutableListOf<BluetoothDevice>()

        if (bluetoothAdapter == null) {
            // Bluetooth не поддерживается
            return deviceList
        }

        // Проверка разрешений для Android 12+
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT)
            != PackageManager.PERMISSION_GRANTED) {
            // Разрешение не получено
            return deviceList
        }

        // Возвращаем список спаренных устройств
        val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
        deviceList.addAll(pairedDevices)

        return deviceList
    }





    }

