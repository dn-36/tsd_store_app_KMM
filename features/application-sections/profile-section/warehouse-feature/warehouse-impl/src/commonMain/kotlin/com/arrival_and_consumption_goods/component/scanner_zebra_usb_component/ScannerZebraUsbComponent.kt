package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.scanner_zebra_usb_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ScannerZebraUsbComponent {

    val viewModel = ScannerZebraUsbViewModel()

    @Composable

    fun Content(){

        val scope = rememberCoroutineScope()

            viewModel.processIntents(ScannerZebraUsbIntents.SetScreen)

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                Text(

                    text = "Подсоедините кабель сканера к телефону и разрешите подключиться к нему нажав затем кнопку да",

                    modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(), fontSize = 16.sp,

                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                viewModel.state.scannerZebraUsb!!.BarcodeData()

            }
        }
    }
}