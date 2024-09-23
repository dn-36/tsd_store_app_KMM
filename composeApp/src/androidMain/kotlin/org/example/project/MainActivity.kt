package org.example.project

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.project.printer_barcode.TSCprinter
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin
import org.koin.android.ext.koin.androidContext
import java.io.IOException
import java.util.UUID


class MainActivity : ComponentActivity() {

    private val askPermissionsBle =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val granted = result.entries.firstOrNull {
                !it.value
            } == null
            if (granted) {
                Log.d("test_11100", "secuesfull")

            } else {
                Log.d("test_11100", "not blaetus")
                //  showError("Для подключения по Bluetooth необхордимы разрешения")
            }
        }
    companion object {
        var device: BluetoothDevice? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       askPermissionsBluetooth()
     //   getPairedDevices(this).map { if(it.name == "RF-BHS") device =  it }

      /*val devicae = connectToDevice(device!!,this)
        TSCprinter.init(device!!)*/


        //Log.d("2323s",device!!.)
        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }
        setContent {
            App.content()
        }
    }


    private fun askPermissionsBluetooth() {
        val perms = mutableListOf<String>()
        perms.add(Manifest.permission.BLUETOOTH)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            perms.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {

            perms.add(Manifest.permission.BLUETOOTH_ADMIN)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            perms.add(Manifest.permission.BLUETOOTH_SCAN)
            perms.add(Manifest.permission.BLUETOOTH_CONNECT)
        }
        askPermissionsBle.launch(perms.toTypedArray())
    }

}



fun connectToDevice(device: BluetoothDevice,context: Context): BluetoothSocket? {
    // UUID — это уникальный идентификатор для подключения, его можно задать самостоятельно или использовать стандартные профили
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
        bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid)
        bluetoothSocket?.connect() // Попытка подключения
        Log.d("ble_успешно","_____________________")
    } catch (e: IOException) {
        Log.d("ble_yt_успешно","_____________________")

        e.printStackTrace()
        try {
            bluetoothSocket?.close() // Закрываем сокет при ошибке
        } catch (closeException: IOException) {
            closeException.printStackTrace()
        }
    }
    return bluetoothSocket
}