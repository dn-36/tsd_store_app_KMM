package com.project.phone

import android.Manifest
import android.os.Build

object PermissionsData {
    val BLUETOOTH_PERMISSION = when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.P -> {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        Build.VERSION.SDK_INT < Build.VERSION_CODES.S -> {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
        else -> {
            listOf(
                Manifest.permission.BLUETOOTH
            )
        }
    }

    val CAMERA_PERMISSION = when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.P -> {
            listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
            listOf(
                Manifest.permission.CAMERA
            )
        }
        else -> {
            listOf(
                Manifest.permission.CAMERA
            )
        }
    }

    val LOCATION_PERMISSION = when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.Q -> {
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
        else -> {
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    val STORAGE_PERMISSION = when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.P -> {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
        else -> {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    val CONTACTS_PERMISSION = when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.P -> {
            listOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS
            )
        }
        Build.VERSION.SDK_INT > Build.VERSION_CODES.P -> {
            listOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            )
        }
        else -> {
            listOf(
                Manifest.permission.READ_CONTACTS
            )
        }
    }
}