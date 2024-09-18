package org.example.project

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.ui.QRCodeMenuScreen
import org.koin.android.ext.koin.androidContext


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askPermissionsBluetooth()
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

class TscSettingsViewModel constructor(
    private val pref: IPreferences
) : ViewModel() {

    val connectionMethodData = MutableLiveData(
        //ConnectMethod.entries
        listOf(
            ConnectMethod.BLUETOOTH
        )
    )

    val settingsData = MutableLiveData(
        pref.tscPrinterSettings
    )

    fun getCurrentSettings() = pref.tscPrinterSettings

    fun selectPrintConnectMethod(m: ConnectMethod) {
        settingsData.value?.let {
            it.connectionMethod = m
            pref.tscPrinterSettings = it
          //  settingsData.notifyObserver()
        }
    }

    fun setBleNac(mac: String) {
        settingsData.value?.let {
            if(it.bleMac != mac) {
                it.bleMac = mac
                pref.tscPrinterSettings = it
                //settingsData.notifyObserver()
            }
        }
    }

}

enum class ConnectMethod(val string:String) {
    BLUETOOTH("connecting_by_ble"),
    USB("connecting_by_usb"),
    WI_FI("connecting_by_wifi");
}


interface IPreferences {

    var fbToken: String?

    fun isAuthorized(): Boolean
    fun logout()


    var tscPrinterSettings: TscPrinterSettings


    var notesPositions: List<String>
    var notesColors: Map<String, String>

}

data class TscPrinterSettings (
    var connectionMethod: ConnectMethod,
    var bleMac: String?
) : IPrinterSettings {

    companion object {
        fun defaultSettings(): TscPrinterSettings {
            return TscPrinterSettings(ConnectMethod.USB, null)
        }
    }

    override fun isEmptySettings(): Boolean {
        return when(connectionMethod) {
            ConnectMethod.BLUETOOTH -> bleMac.isNullOrEmpty()
            else -> false
        }
    }
}

interface IPrinterSettings {
    fun isEmptySettings() : Boolean
}