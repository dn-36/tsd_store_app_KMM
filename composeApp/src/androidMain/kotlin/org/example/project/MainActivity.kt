package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.phone.AskPermissions
import com.project.phone.TSCprinter
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.ui.ListBluetoothDevicesComponent
import org.koin.android.ext.koin.androidContext


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AskPermissions(this).execute(AskPermissions.PPERMISSION.BLUETOOTH, {}, {})
        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }
        setContent {
            App.content()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TSCprinter(this).stopBluetoothDiscovery()
    }


}


@Preview
@Composable
fun PreviewComponent(){
   ListBluetoothDevicesComponent(listOf("Divice 1", "Device 2", "Device 3"),{},{},{})
}
