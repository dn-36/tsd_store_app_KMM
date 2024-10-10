package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.project.phone.PermissionManeger
import org.example.project.app.ui.App
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }


        PermissionManeger(this).askPermissions(
            PermissionManeger.PERMISSION.BLUETOOTH_PERMISSION
        )

        setContent {
            App.Content()
        }
    }
}



