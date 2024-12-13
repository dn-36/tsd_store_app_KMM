package com.arrival_and_consumption_goods

import android.view.InputDevice
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrival_and_consumption_goods.viewmodel_bluetooth_or_adapter.ScannerBluetoothOrAdapterIntents
import com.arrival_and_consumption_goods.viewmodel_bluetooth_or_adapter.ScannerBluetoothOrAdapterViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

actual class DetailScreen actual constructor() {

    val viewModel = ScannerBluetoothOrAdapterViewModel()

    @Composable

    actual fun Content( deviceId: Int, onBackPressed:() -> Unit ) {

        val barcodeBuffer = remember { StringBuilder() }

        val requester = remember { FocusRequester() }

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(
                modifier = Modifier
                    .focusRequester(requester)
                    .focusable(true)
                    .padding(16.dp)
                    .onKeyEvent { event ->

                        if (event.type == KeyEventType.KeyDown) {
                            val char = event.nativeKeyEvent.unicodeChar.toChar()
                            if (event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {

                                viewModel.processIntents(

                                    ScannerBluetoothOrAdapterIntents.UpdateScanData(

                                        barcodeBuffer.toString()))

                               // receivedData = barcodeBuffer.toString() // Устанавливаем готовую строку

                                barcodeBuffer.clear() // Очищаем буфер
                            } else {
                                barcodeBuffer.append(char)
                            }
                        }
                       /* if (event.type == KeyEventType.KeyDown) {
                            receivedData = "${decoder.handleKey(event.nativeKeyEvent)}"
                            Toast.makeText(context, "Результат: $receivedData", Toast.LENGTH_SHORT).show()
                            Log.d("KeyEventjjjjjjj", "KeyCode: ${event.nativeKeyEvent.keyCode}, Char: ${event.nativeKeyEvent.unicodeChar.toChar()}")
                        }*/
                        true
                    }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onBackPressed() }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Имя: ${InputDevice.getDevice(deviceId)?.name}",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Id: ${InputDevice.getDevice(deviceId)?.id}",
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Полученные данные:",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = viewModel.state.scanData,
                    fontSize = 22.sp,
                    modifier = Modifier
                       /* .clickable {
                            (decoder as? Decoder.Keyboard)?.clearTemp()
                            receivedData = ""
                        }*/
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(

                    onClick = {  },

                    modifier = Modifier

                        .clip(RoundedCornerShape(70.dp))

                        .height(40.dp)

                        .fillMaxWidth()

                ) {

                    Text(text = "Добавить")

                }
            }
        }

        SideEffect {
            requester.requestFocus()
        }
    }
}