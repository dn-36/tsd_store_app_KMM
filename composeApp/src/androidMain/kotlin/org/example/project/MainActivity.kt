package org.example.project

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.Manifest
import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.project.phone.PermissionManeger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.project.app.ui.App
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }


        val permissionManeger =  PermissionManeger(this)


            permissionManeger.askPermissions(
                PermissionManeger.PERMISSION.CAMERA_PERMISSION
            )
            permissionManeger.askPermissions(
                PermissionManeger.PERMISSION.BLUETOOTH_PERMISSION
            )
            permissionManeger.askPermissions(
                PermissionManeger.PERMISSION.CONTACTS_PERMISSION
            )


        setContent {

                App.Content()

        }
    }
}



