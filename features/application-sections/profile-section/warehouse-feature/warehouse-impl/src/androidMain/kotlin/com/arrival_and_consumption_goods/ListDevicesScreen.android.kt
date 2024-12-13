package com.arrival_and_consumption_goods

import android.view.InputDevice
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

actual class ListDevicesScreen actual constructor() {

    @Composable

    actual fun Content( onClickItem:( id: Int ) -> Unit, onClickBack:() -> Unit ) {

        val inputDevices = InputDevice.getDeviceIds().map { InputDevice.getDevice(it) }

        Box( modifier = Modifier.fillMaxSize().background(Color.White) ) {

            Column( modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),

                horizontalAlignment = Alignment.CenterHorizontally ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding( start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Доступные устройства", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {

                    items(inputDevices) { inputDevice ->
                        DeviceItem(
                            modifier = Modifier

                                .clickable { onClickItem(inputDevice?.id ?: 0) },

                            item = inputDevice!!
                        )
                    }
                }
            }
        }
    }
}