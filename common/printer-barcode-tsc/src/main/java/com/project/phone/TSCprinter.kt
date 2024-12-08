package com.project.phone

import android.Manifest
import android.app.Activity
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
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.graphics.scale
import com.example.tscdll.TSCActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.UUID

 class TSCprinter(private val context: Context) {
     private val tscDll = TSCActivity()

     private var _device: BluetoothDevice? = null
     private val deviceList = mutableListOf<BluetoothDevice>()
     private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    // private val foundDevices = mutableListOf<String>()
     //private var _actionSecuesfull: () -> Unit = {}
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

                         //_actionSecuesfull()

                     }
                 }
                 BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {

                     Log.d("LLLL__LLLL", "ACTION_DISCOVERY_FINISHED")

                     //// Завершен процесс поиска
                     //unregisterReceiver(this)

                 }

             }
         }
     }

     fun print(barCode: Bitmap,
               title: Bitmap,
               heightTicket:Int,
               widthTicket:Int,
               xQrCode:Int,
               yQrCode:Int
               ) {




       //  val countChars = ("barcode").chars().count().toInt()

         try {

             tscDll.openport(
                 _device!!.address
             )


             tscDll.sendcommand("SIZE ${heightTicket} mm, ${widthTicket} mm\r\n")
             tscDll.sendcommand("GAP 3 mm, 0 mm\r\n")
             tscDll.sendcommand("CODEPAGE UTF-8\r\n")
             tscDll.downloadttf("ARIAL.TTF")
             tscDll.setup(widthTicket, heightTicket, 4, 3, 0, 1, 0)
             tscDll.clearbuffer()

             tscDll.sendbitmap(xQrCode,yQrCode,barCode)//.scale(barCode.width/2,barCode.height))
             tscDll.sendbitmap(xQrCode+((barCode.width/2)-(title.width/2)),yQrCode+10+barCode.height,title)
             tscDll.printlabel(1, 1)
             tscDll.closeport(1000)
         } catch (e: Exception) {
             Log.d("errora", e.message.toString())
         }
     }




     fun searchForDevices(
         actionAddDevice: (String) -> Unit,
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
        val deviceList = deviceList.distinctBy { it.address }
         stopBluetoothDiscovery()
         return deviceList.distinctBy { it.address }
     }


     fun stopBluetoothDiscovery() {
        /* context.unregisterReceiver(receiver)
         bluetoothAdapter?.cancelDiscovery()*/
        // val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

         if (bluetoothAdapter?.isDiscovering == true) {
             bluetoothAdapter.cancelDiscovery()
             Log.d("LLLL__LLLL","Bluetooth discovery stopped.")
         } else {
             Log.d("LLLL__LLLL","Bluetooth discovery was not active.")
         }
     }


      suspend fun connectToDevice(
          device: String,
          context: Context,
          actionSuccessfully: () -> Unit,
          actionError: () -> Unit,
          ): BluetoothSocket? {
         val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
         var bluetoothSocket: BluetoothSocket? = null

         try {
             // Создаем BluetoothSocket для соединения
             if (ActivityCompat.checkSelfPermission(
                     context,
                     Manifest.permission.BLUETOOTH_CONNECT
                 ) != PackageManager.PERMISSION_GRANTED
             ) {
              /*   withContext(Dispatchers.Main) {
                     Toast.makeText(context,
                         "Нет нужных разрешений что бы использовать этот функционал!",
                         Toast.LENGTH_SHORT).show()
                 }*/
             //    return null
             }
             Log.d("111qqq",deviceList.map { it.name }.toString())
             deviceList.forEach {
                 if (it.name == device) {
                     _device = it
                 }
             }
             if(_device != null) {
                 bluetoothSocket = _device!!.createRfcommSocketToServiceRecord(uuid)
                 bluetoothSocket?.connect()
               actionSuccessfully()
             }else{
               actionError()
             }


         } catch (e: IOException) {
             actionError()
             e.printStackTrace()
             try {
                 bluetoothSocket?.close()
             } catch (closeException: IOException) {
                 closeException.printStackTrace()
             }
         }
         return bluetoothSocket
     }
 }




