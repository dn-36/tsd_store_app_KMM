package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods;

import android.os.Handler
import android.os.Message
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zebra.barcode.sdk.sms.ConfigurationUpdateEvent
import com.zebra.scannercontrol.DCSSDKDefs
import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.FirmwareUpdateEvent
import com.zebra.scannercontrol.IDcsSdkApiDelegate
import com.zebra.scannercontrol.SDKHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.mp.KoinPlatform.getKoin


actual class ScannerZebraUsbScreen actual constructor( ) {

    val viewModel = ScannerZebraUsbViewModel(getKoin().get())

    @Composable

     actual fun Content() {

         //viewModel.processIntents(ScannerZebraUsbIntents.SetScreen)

       // val scope = rememberCoroutineScope()

        viewModel.customization()

        println("///// Content() ${viewModel.state.scanData}//////")

        Box( modifier = Modifier.fillMaxSize().background(Color.White) ) {

            Column( modifier = Modifier.fillMaxSize().padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally ) {

                Text(

                    text = "Подсоедините кабель сканера к телефону и разрешите подключиться к нему нажав затем кнопку да",

                    modifier = Modifier.fillMaxWidth(0.9f), fontSize = 16.sp,

                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = viewModel.state.scanData, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.scannersListHasBeenUpdated() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Подключиться")
                }
            }
        }
    }
}


