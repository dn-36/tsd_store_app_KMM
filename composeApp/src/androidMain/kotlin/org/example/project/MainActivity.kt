package org.example.project

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.project.printer_barcode.TSCprinter
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.ui.ListBluetoothDevicesComponent
import org.koin.android.ext.koin.androidContext


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }


// Не забудьте вызвать cleanup, когда завершите работу


        setContent {
            App.content()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        TSCprinter(this).stopBluetoothDiscovery()
    }


    class BluetoothSearcher(private val context: Context) {

        private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        private val foundDevices = mutableListOf<String>()
        private var _actionSecuesfull:()->Unit = {}
        private var _actionAddDevice:(String)->Unit = {}
        // BroadcastReceiver для поиска устройств
        private val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    BluetoothDevice.ACTION_FOUND -> {

                        val device: BluetoothDevice? =
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        device?.let {
                            val deviceName =
                                it.name ?: it.address // Если имя недоступно, используем адрес
                            foundDevices.add(deviceName)
                            _actionSecuesfull
                            Log.d("Получаем устройство", deviceName)


                        }
                    }

                }
            }
        }

        // Функция для поиска Bluetooth-устройств
        fun searchForDevices(actionAddDevice:(String)->Unit,actionSecuesfull:()->Unit): List<String> {
            _actionAddDevice = actionAddDevice
            // Проверка разрешений
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Здесь вам нужно запросить разрешение на доступ к местоположению, если его нет
                return emptyList()
            }

            foundDevices.clear()

            // Регистрируем BroadcastReceiver
            val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
            context.registerReceiver(receiver, filter)

            // Запускаем поиск устройств
            bluetoothAdapter?.startDiscovery()

            actionSecuesfull()
            return foundDevices
        }

        // Не забудьте отменить регистрацию ресивера, когда он больше не нужен
        fun cleanup() {
            context.unregisterReceiver(receiver)
            bluetoothAdapter?.cancelDiscovery()
        }
    }

    fun askPermissionsBluetooth(actionSecuesfull: () -> Unit, actionFail: () -> Unit) {
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
        askPermissionsBle(actionSecuesfull, actionFail).launch(perms.toTypedArray())
    }
    private fun askPermissionsBle(actionSecuesfull: () -> Unit, actionFail:()->Unit) =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val granted = result.entries.firstOrNull {
                !it.value
            } == null

            if (granted) {
                actionSecuesfull()
            } else {
                actionFail()
                Toast.makeText(
                    this,
                    "для использования функционала нужно получить от вас разрешения",
                    Toast.LENGTH_SHORT
                )
            }

        }

}


@Preview
@Composable
fun PreviewComponent(){
   ListBluetoothDevicesComponent(listOf("Divice 1", "Device 2", "Device 3"),{})
}
