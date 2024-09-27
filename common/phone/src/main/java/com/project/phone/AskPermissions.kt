package com.project.phone

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class AskPermissions(private val context: Activity) {
    fun execute(
        permission: PPERMISSION,
        actionSecuesfull: () -> Unit,
        actionFail: () -> Unit
    ) {
        actionPermissionPermissionsBle(
            context as ComponentActivity,
            actionSecuesfull,
            actionFail
        ).launch(permission.permissionList.toTypedArray())
    }

    private fun actionPermissionPermissionsBle(
        activity: ComponentActivity,
        actionSecuesfull: () -> Unit,
        actionFail: () -> Unit
    ) =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val granted = result.entries.firstOrNull {
                !it.value
            } == null

            if (granted) {
                actionSecuesfull()
            } else {
                actionFail()
            }
        }

    enum class PPERMISSION(val permissionList: List<String>) {
        BLUETOOTH(PermissionsData.BLUETOOTH_PERMISSION)
    }
}
