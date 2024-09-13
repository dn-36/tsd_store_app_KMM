package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.project.presentation.core.App
import org.example.project.presentation.core.initKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin{
           androidContext(this@MainActivity.application)
            this@MainActivity.application

        }

        setContent {
            App()
        }


    }

}
