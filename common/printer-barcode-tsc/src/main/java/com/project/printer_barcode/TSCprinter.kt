package com.project.printer_barcode

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.tscdll.TSCActivity
import java.io.IOException
import java.util.UUID

 class TSCprinter(private val context: Context) {
     private val tscDll = TSCActivity()

     private var _device: BluetoothDevice? = null
     private val deviceList = mutableListOf<BluetoothDevice>()
     private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    // private val foundDevices = mutableListOf<String>()
     private var _actionSecuesfull: () -> Unit = {}
     private var _actionAddDevice: (String) -> Unit = {}

     private val receiver = object : BroadcastReceiver() {
         override fun onReceive(context: Context, intent: Intent) {
             val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter?.bondedDevices!!
             pairedDevices.forEach{ device ->
                 if(!deviceList.any { it.address == device.address }) {
                     _actionAddDevice(device.name)
                     deviceList.add(device)
                 }
             }
             when (intent.action) {
                 BluetoothDevice.ACTION_FOUND -> {

                     val device: BluetoothDevice? =
                         intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                     device?.let {
                         //_actionAddDevice(deviceList.size.toString())
                         if(!deviceList.any { it.address == device.address } && it.name != null){

                                 deviceList.add(it)


                         val deviceName =
                             it.name?:"" //it.address // Если имя недоступно, используем адрес
                         _actionAddDevice(deviceName)

                        }

                         _actionSecuesfull()

                     }
                 }
                 BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                     // Завершен процесс поиска
                     //unregisterReceiver(this)

                 }

             }
         }
     }

     fun print(barCode: Bitmap, title: Bitmap, ) {

         val heightQRcode = 25
         val densty = 4

         val widthTicket = 70
         val heightTicket = 50
         val cefWidthQrCode = 2
         val countChars = ("barcode").chars().count().toInt()

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




     // Функция для поиска Bluetooth-устройств
     fun searchForDevices(
         actionAddDevice: (String) -> Unit,
         actionSecuesfull: () -> Unit
     ): List<BluetoothDevice> {
         _actionAddDevice = actionAddDevice
         // Проверка разрешений
         if (ActivityCompat.checkSelfPermission(
                 context,
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED
         ) {

             return emptyList()
         }

         deviceList.clear()

         // Регистрируем BroadcastReceiver
         val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
         context.registerReceiver(receiver, filter)

         // Запускаем поиск устройств
         bluetoothAdapter?.startDiscovery()

         //val filterAdress:List<String> = deviceList.map { it.address }.toSet().toList()
          Log.d("test_111_111a",deviceList.distinctBy { it.address }.map { it.address }.toString())
         return deviceList.distinctBy { it.address }
     }


     fun cleanup() {
         context.unregisterReceiver(receiver)
         bluetoothAdapter?.cancelDiscovery()
     }


     fun connectToDevice(device: String, context: Context): BluetoothSocket? {
         val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
         var bluetoothSocket: BluetoothSocket? = null

         try {
             // Создаем BluetoothSocket для соединения
             if (ActivityCompat.checkSelfPermission(
                     context,
                     Manifest.permission.BLUETOOTH_CONNECT
                 ) != PackageManager.PERMISSION_GRANTED
             ) {

                 return null
             }
             Log.d("111qqq",deviceList.map { it.name }.toString())
             deviceList.forEach {
                 if (it.name == device) {
                     _device = it
                 }
             }

             bluetoothSocket = _device!!.createRfcommSocketToServiceRecord(uuid)
             bluetoothSocket?.connect()

         } catch (e: IOException) {

             e.printStackTrace()
             try {
                 bluetoothSocket?.close() // Закрываем сокет при ошибке
             } catch (closeException: IOException) {
                 closeException.printStackTrace()
             }
         }
         return bluetoothSocket
     }
 }



data class S(val a: String, val b: Int)

