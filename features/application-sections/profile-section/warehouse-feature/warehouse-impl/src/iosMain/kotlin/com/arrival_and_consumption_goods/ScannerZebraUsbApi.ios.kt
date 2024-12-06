package com.arrival_and_consumption_goods

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text


actual class ScannerZebraUsbScreen actual constructor(){

    @Composable

    actual fun Content(){

        Box (modifier = Modifier.fillMaxSize().background(Color.White)) {

            Text("На доступна только на android")

        }

    }

}