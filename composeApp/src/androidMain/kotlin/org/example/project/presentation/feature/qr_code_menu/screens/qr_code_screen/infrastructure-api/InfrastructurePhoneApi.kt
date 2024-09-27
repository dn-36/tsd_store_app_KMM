package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.infrastructure

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

interface InfrastructurePhoneApi {

    fun askPermissionsBluetooth(
        context: Context,
        actionSecuesfull: () -> Unit,
        actionFail: () -> Unit
    ) {
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
        actionPermissionPermissionsBle(context as ComponentActivity,actionSecuesfull, actionFail).launch(perms.toTypedArray())
    }
    private fun actionPermissionPermissionsBle(activity: ComponentActivity, actionSecuesfull: () -> Unit, actionFail:()->Unit) =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val granted = result.entries.firstOrNull {
                !it.value
            } == null

            if (granted) {
                actionSecuesfull()
            } else {
                actionFail()
              /*  Toast.makeText(
                    this,
                    "для использования функционала нужно получить от вас разрешения",
                    Toast.LENGTH_SHORT
                )*/
            }

        }

}