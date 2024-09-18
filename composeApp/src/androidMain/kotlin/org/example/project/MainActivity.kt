package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.ui.QRCodeMenu
import org.koin.android.ext.koin.androidContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity.applicationContext)
        }
        setContent {
           App.content()
        }
    }
    }

@Preview
@Composable
fun PreviewTool(){
    QRCodeMenu.Content()
}




