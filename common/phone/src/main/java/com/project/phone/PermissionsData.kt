package com.project.phone

import android.Manifest
import android.os.Build

object PermissionsData {
 val BLUETOOTH_PERMISSION =

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
            )
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_FINE_LOCATION

            )
        } else{
            listOf(
                Manifest.permission.BLUETOOTH
            )
        }
}