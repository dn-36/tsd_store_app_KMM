package com.project.phone

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionManeger(private val context: Context) {
    fun askPermissions(
        vararg permissions: PERMISSION,

        ) {
        val _permissions = mutableListOf<String>()

        permissions.map {
            _permissions.addAll(it.permissionList)
        }

        actionPermissionPermissionsBle(
            context as ComponentActivity,

        ).launch(_permissions.toTypedArray())
    }

    fun isHasPermissions(context: Context, permissions: List<String>): Boolean {
        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun actionPermissionPermissionsBle(
        activity: ComponentActivity,

    ) =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

    enum class PERMISSION(val permissionList: List<String>) {
        BLUETOOTH_PERMISSION(PermissionsData.BLUETOOTH_PERMISSION),
        CAMERA_PERMISSION(PermissionsData.CAMERA_PERMISSION),
        LOCATION_PERMISSION(PermissionsData.LOCATION_PERMISSION),
        STORAGE_PERMISSION(PermissionsData.STORAGE_PERMISSION),
        CONTACTS_PERMISSION(PermissionsData.CONTACTS_PERMISSION),
    }
}


