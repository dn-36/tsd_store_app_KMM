package org.example.project.presentation.feature.qr_code.screens.settings_ticket_tsc_printer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import tsdstorekmm.composeapp.generated.resources.Res
import tsdstorekmm.composeapp.generated.resources.bluetooth


object SettingsTicketsTSCprinter: Screen  {

    var heightTicketMM = 50
    var widthTicketMM = 50


    @Composable
    override fun Content() {


        var x by remember { mutableStateOf(0F) }
        var y by remember { mutableStateOf(0F) }

        var _hieghtTicketMM by remember { mutableStateOf(50) }
        var _widthTicketMM by remember { mutableStateOf(50) }

        var expendWidth by remember { mutableStateOf(false) }
        var expendHeight by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    //  .clickable { actionCloseSettings() }
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxHeight(0.85f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {
                //   var fontSize by remember { mutableStateOf(16f) }
                //   var imageSize by remember { mutableStateOf(20f) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    //Text("Настройки печати:", color = Color.Black, fontSize = 20.sp)

                    //Spacer(modifier = Modifier.height(30.dp))

                    Box(modifier = Modifier.width(
                        if( _widthTicketMM > 10) {_widthTicketMM.dp*5}else{25.dp}
                    )
                        .height(
                            if( _hieghtTicketMM > 10)_hieghtTicketMM.dp*5 else {25.dp}
                        )
                        .border(
                            width = 1.dp,color = Color.Black, RoundedCornerShape(15.dp)
                        )
                        , contentAlignment = Alignment.TopStart) {

                        Column(
                            modifier = Modifier.padding(start = x.dp*5, top = y.dp*5),
                            horizontalAlignment = Alignment.CenterHorizontally) {


                            Image(painter = painterResource(Res.drawable.bluetooth),
                                contentDescription = null,
                                modifier = Modifier.height(60.dp)
                                    .width(60.dp)
                            )
                            /* Image(
                                 bitmap = qrCode.asImageBitmap(),
                                 contentDescription = null,
                                 modifier = Modifier
                                     .height((heightQrCode * 3.5F).dp)
                                     .width(150.dp)
                             )*/

                            //Spacer(modifier = Modifier.height(32.dp))
                            Spacer(modifier = Modifier.height(10.dp))


                            Text("Продукт", fontSize = 20.sp)
                            /*Image(
                                bitmap = title.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(130.dp)
                                    .width(250.dp)
                            )*/
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Row(modifier = Modifier.fillMaxWidth(0.75f), horizontalArrangement = Arrangement.SpaceBetween){

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Ширина",fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(1.dp))
                            OutlinedTextField(
                                value =if( _widthTicketMM == -1) {""}else{ _widthTicketMM.toString()},
                                onValueChange = {
                                    if(it.isNotBlank()) {
                                        _widthTicketMM = it.toInt()
                                         widthTicketMM = it.toInt()
                                    }else{
                                        _widthTicketMM = -1
                                        widthTicketMM = 10
                                    }

                                },
                                label = { },
                                textStyle = TextStyle(fontSize = 12.sp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number // Установка клавиатуры на числовую
                                ),
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(55.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { }// Стандартная высота TextField
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Высота",fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(1.dp))
                            OutlinedTextField(
                                value = if( _hieghtTicketMM == -1) {""}else{ _hieghtTicketMM.toString()},
                                onValueChange = {
                                    if(it.isNotBlank()) {
                                        _hieghtTicketMM = it.toInt()
                                        heightTicketMM = it.toInt()
                                    }else{
                                        _hieghtTicketMM = -1
                                        heightTicketMM = 10
                                    }
                                },
                                textStyle = TextStyle(fontSize = 12.sp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number // Установка клавиатуры на числовую
                                ),
                                label = { },
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(55.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { }// Стандартная высота TextField
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Column {

                        Text("Ось x:", color = Color.Black, fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("${(_widthTicketMM).toInt()}", color = Color.Black, fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Slider(
                                value = x,
                                onValueChange = {
                                    x = it
                                },
                                valueRange = 0f..50f,  // Диапазон изменения шрифта
                                modifier = Modifier.fillMaxWidth()
                            )

                        }

                    }
                    Column {

                        Text("Ось y:", color = Color.Black, fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("${_hieghtTicketMM}", color = Color.Black, fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Slider(
                                value = y,
                                onValueChange = {
                                   y = it
                                },
                                valueRange = 0F..50F,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    Button(
                        onClick = { //actionSavedSettings()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .height(40.dp)
                            .fillMaxWidth(0.95f)
                    ) {
                        Text(text = "Сохранить")
                    }

                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.03f)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            )

        }
    }
}


