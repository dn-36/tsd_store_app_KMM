package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components

import android.graphics.Bitmap
import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.NonCancellable.isActive




    @Composable
     fun SettingsTicketsTSCprinterComponent(
        barcode:Bitmap,
        text:Bitmap,
       /* heightTicketMM:Int,
        widthTicketMM:Int,*/
        actioTscPrinter:(x:Int,y:Int,height:Int,widht:Int)->Unit
     ) {


        var x by remember { mutableStateOf(42F) }
        var y by remember { mutableStateOf(90F) }

        var _hieghtTicketMM by remember { mutableStateOf(50) }
        var _widthTicketMM by remember { mutableStateOf(50) }
        var heightTicketMM by remember { mutableStateOf(0) }
        var widthTicketMM by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            //Text("Настройки печати:", color = Color.Black, fontSize = 20.sp)

            //Spacer(modifier = Modifier.height(30.dp))
            Column {

                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .clipToBounds().width(
                            if (_widthTicketMM > 10) {
                                (_widthTicketMM * 4.5).dp
                            } else {
                                25.dp
                            }
                        )
                        .height(
                            if (_hieghtTicketMM > 10) (_hieghtTicketMM * 4.5).dp else {
                                25.dp
                            }
                        )
                        .border(
                            width = 1.dp, color = Color.Black, RoundedCornerShape(15.dp)
                        ).pointerInput(this@Column) {

                            /* detectTapGestures { offset ->
                              x =  offset.x
                              y = offset.y
                            }*/
                            awaitPointerEventScope {
                                while (isActive) {
                                    val event = awaitPointerEvent()
                                    event.changes.forEach { change ->
                                        x += (change.positionChange().x / 2)
                                        y += (change.positionChange().y / 2)
                                    }
                                    actioTscPrinter(
                                        (x.toInt()*1.5).toInt(),
                                        (y.toInt()*1.5).toInt(),
                                        heightTicketMM,
                                        widthTicketMM
                                    )
                                }
                            }
                        }, contentAlignment = Alignment.TopStart
                ) {

                    Column(
                        modifier = Modifier.offset(x.dp, y.dp)
                            .align(Alignment.TopStart),//.padding(start = x.dp*5, top = y.dp*5),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Image(
                            bitmap = barcode.asImageBitmap(),//painter = painterResource(Res.drawable.bluetooth),
                            contentDescription = null,
                            modifier = Modifier
                                .width((barcode.width / 2).dp)
                                .height((barcode.height / 2).dp)
                        )
                        /* Image(
                                 bitmap = qrCode.asImageBitmap(),
                                 contentDescription = null,
                                 modifier = Modifier
                                     .height((heightQrCode * 3.5F).dp)
                                     .width(150.dp)
                             )*/

                        //Spacer(modifier = Modifier.height(32.dp))


                        Image(
                            bitmap = text.asImageBitmap(),//painter = painterResource(Res.drawable.bluetooth),
                            contentDescription = null,
                            modifier = Modifier
                                .width((text.width / 10).dp)
                                .height((text.height / 4).dp)
                        )
                        /*Image(
                                bitmap = title.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(130.dp)
                                    .width(250.dp)
                            )*/
                    }
                }

            }


            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier.fillMaxWidth(0.75f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ширина", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(1.dp))
                    OutlinedTextField(
                        value = if (_widthTicketMM == -1) {
                            ""
                        } else {
                            _widthTicketMM.toString()
                        },
                        onValueChange = {
                            x = 0F
                            y = 0F
                            if (it.isNotBlank()) {
                                _widthTicketMM = it.toInt()
                                widthTicketMM = it.toInt()
                                actioTscPrinter(
                                    (x.toInt()*1.5).toInt(),
                                    (y.toInt()*1.5).toInt(),
                                    heightTicketMM,
                                    widthTicketMM
                                )

                            } else {
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
                    Text("Высота", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(1.dp))
                    OutlinedTextField(
                        value = if (_hieghtTicketMM == -1) {
                            ""
                        } else {
                            _hieghtTicketMM.toString()
                        },
                        onValueChange = {
                            x = 0F
                            y = 0F
                            if (it.isNotBlank()) {
                                _hieghtTicketMM = it.toInt()
                                heightTicketMM = it.toInt()
                                actioTscPrinter(
                                    (x.toInt()*1.5).toInt(),
                                    (y.toInt()*1.5).toInt(),
                                    heightTicketMM,
                                    widthTicketMM
                                )
                            } else {
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
        }
    }
 /*                 Spacer(modifier = Modifier.height(5.dp))



                 Spacer(modifier = Modifier.height(22.dp))

                 Button(
                     onClick = {
                         Log.d("x,y","x = ${x} y = ${y}")
                         actioTscPrinter(
                             (x.toInt()*1.5).toInt(),
                             (y.toInt()*1.5).toInt(),
                             heightTicketMM,
                             widthTicketMM
                         )
                     },
                     modifier = Modifier
                         .clip(RoundedCornerShape(50.dp))
                         .height(40.dp)
                         .fillMaxWidth(0.95f)
                 ) {
                     Text(text = "Сохранить")
                 }

             }

             Box(Modifier.fillMaxWidth().height(40.dp))

         }
         Box(
             modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight(0.03f)
                 .background(Color.White)
                 .align(Alignment.BottomCenter)
         )

     }
 }*/



