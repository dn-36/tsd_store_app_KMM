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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import networking.ApiClient
import networking.AuthorizationClient
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
        CoroutineScope(Dispatchers.IO).launch {
            println("||||||||||||||||||||||||||||||||||||||_______|||||||||||||||||||||||||||||||||")
           println(ApiClient(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMz" +
                        "FmMjEyNTljYTMwOWU0YTRiMDU5NWMyODQ0ZmIzMWM4MDY0YTE5YmZlZGYwYjA4OWYwYmQzYjU1ZDljZGMwO" +
                        "DlkNDRiMDFmZjA3ZTgwNmEiLCJpYXQiOjE3MjYyNDAwOTcuODkyODYyLCJuYmYiOjE3MjYyNDAwOTcuODky" +
                        "ODY1LCJleHAiOjE3NTc3NzYwOTcuODU4NzQyLCJzdWIiOiIyNTQiLCJzY29wZXMiOlsidXNlcl9zZXJ2aWN" +
                        "lIl19.zfXlDgMPv3YeW-CJ_0851uuPR3PJuVCFBEjtq7FGcAVaShmy6xI9ZAAveKTvN5_8VOVWlZOTgu9_Y" +
                        "2b2FjuRWiKmzcZChbHUFy7PGhVUJC33sL7-er9eav50I-dMdP7w7J5KlCqZqru8x4TCqaR0VgOihurz8cWJ" +
                        "0j9qEipUe8aj9g2DAymzI7Cv7J5LgMbz8iCpAlhzEjJ7-u3mN0mMFjWvSbXqMT6KmxYVPbDjC1NHJ_wXKaA" +
                        "7dMTN32seXW0OQgQsx2hMm6WB3GWZpY-szyiSPNqG4c6fZEjfMUH2mVAjCpUvvJasva6_TdrInQpnf-aYXI" +
                        "e27qgCFHMD8X0WfAj4RdWAZ3bZFzEik4n5ZFzT8Bg7Hu3BRUVWFZuSVekGPn7hrvF3yhrZLHF_jXVL0Y-Hv" +
                        "ywbxvNrTPYI1QW5J744NUKY423ninLKflPxnIiSzPFGLj-iHL-3qPOQKPz6F_fzcx-SdwSROPl244mNUnlf" +
                        "jJq7CeQKYyzLHK7ZoDXYQycpAV7dIKrZHoW-2F0FC49YyAKf8dHKLfkjX-rYrmg_QeAtng31rSpADtpimug" +
                        "H9Gzv6Ak3lOvSHHJAfDFPvPpbNxixgt36y5DvCZPlPcFpp-KUhqIfqPYRFUAO_qCamaAALpQyknOYCB1zW" +
                        "cedzSX-j_oyMEQcECcu9Y2MjpE"
            ).getProductNames().toString())
            println("||||||||||||||||||||||||||||||||||||||_______|||||||||||||||||||||||||||||||||")

        }
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
