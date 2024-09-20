package org.example.project

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.project.presentation.core.ConstData
import product_network.ProductApiClient
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin
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
        /*    println("||||||||||||||||||||||||||||||||||||||_______|||||||||||||||||||||||||||||||||")
           println(
               ProductApiClient(
                  ConstData.TOKEN
            ).getProductNames().toString())
            println("||||||||||||||||||||||||||||||||||||||_______|||||||||||||||||||||||||||||||||")
*/
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
