package com.project.core_app.ui.components.settings_tickets

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.ui.components.settings_tickets.viewmodel.SettingsTicketsIntent
import com.project.core_app.ui.components.settings_tickets.viewmodel.SettingsTicketsViewModel
import com.project.core_app.viewmodel.QRcodeMenuIntent
import kotlinx.coroutines.NonCancellable.isActive
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.down_arrow
import project.core.resources.error
import project.core.resources.right_arrow




class SettingsTicketsTSCprinter(
   private val  viewModel:SettingsTicketsViewModel = SettingsTicketsViewModel()
) {



    @Composable
    fun Component(
        barcode: Bitmap,
        text: Bitmap,
        heightQrCode: Int,
        actioTscPrinter: (x: Int, y: Int, height: Int, widht: Int) -> Unit
    ) {
        var squareWidth by remember { mutableStateOf(viewModel.state.widthTicketMM*4.5) }
        var squareHeight by remember { mutableStateOf(viewModel.state.heightTicketMM*4.5) }

        LaunchedEffect(barcode.width+barcode.height+text.width) {
            viewModel.processIntent(
                SettingsTicketsIntent.SetCoordinatesQRcode(
                    viewModel.state.x,
                    viewModel.state.y,
                    barcode,
                    text,
                    viewModel.state.heightTicketMM,
                    viewModel.state.widthTicketMM,
                    heightQrCode,
                    actioTscPrinter = actioTscPrinter
                )
            )


            Log.d("qwert_", heightQrCode.toString())
        }

        Column(
            modifier = Modifier.fillMaxWidth(0.95F).fillMaxHeight(0.5F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column {
                    Box( modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        ) {
                        Image(
                            painter = painterResource(Res.drawable.down_arrow),
                            modifier = Modifier.fillMaxSize(0.1F)
                                .align(Alignment.BottomCenter)
                                .pointerInput(Unit) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()

                                            squareHeight =  squareHeight + (dragAmount.y/2)
                                        viewModel.state = viewModel.state.copy(
                                            heightTicketMM = (squareHeight/4.5).toInt()
                                        )
                                        Log.d("tttt", viewModel.state.heightTicketMM.toString())

                                        viewModel.processIntent(
                                            SettingsTicketsIntent.SetCoordinatesQRcode(
                                             //   viewModel.state.x,
                                             //   viewModel.state.y,
                                              barcode =   barcode,
                                              text =   text,
                                              height =   viewModel.state.heightTicketMM,
                                              widht =   viewModel.state.widthTicketMM,
                                              heightQrCode = heightQrCode,
                                              actioTscPrinter = actioTscPrinter
                                            )
                                        )

                                    }
                                },
                            contentDescription = null
                        )
                        Image(
                            painter = painterResource(Res.drawable.right_arrow),
                            modifier = Modifier
                                .fillMaxSize(0.1F)
                                .align(Alignment.CenterEnd)
                                .pointerInput(Unit) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()
                                        squareWidth =  squareWidth + (dragAmount.x/2)
                                        viewModel.state = viewModel.state.copy(
                                            widthTicketMM = (squareWidth/4.5).toInt()
                                        )
                                        Log.d("tttt", viewModel.state.widthTicketMM.toString())
                                        viewModel.processIntent(
                                            SettingsTicketsIntent.SetCoordinatesQRcode(
                                                viewModel.state.x,
                                                viewModel.state.y,
                                                barcode,
                                                text,
                                                viewModel.state.heightTicketMM,
                                                viewModel.state.widthTicketMM,
                                                heightQrCode = heightQrCode,
                                                actioTscPrinter = actioTscPrinter
                                            )
                                        )
                                    }
                                },
                            contentDescription = null
                        )

                        Box(
                            modifier = Modifier
                                .padding(40.dp)
                                .align(Alignment.Center)
                                .clipToBounds()
                                .width(
                                    if (viewModel.state.widthTicketMM > 10) {
                                        ( viewModel.state.widthTicketMM * 4.5).dp

                                    } else {
                                        25.dp
                                    }
                                )
                                .height(
                                    if (viewModel.state.heightTicketMM > 10)
                                            (viewModel.state.heightTicketMM * 4.5).dp
                                    else {
                                        25.dp
                                    }
                                )
                                .border(
                                    width = 1.dp, color = Color.Black, RoundedCornerShape(15.dp)
                                ).pointerInput(this@Column) {

                                    awaitPointerEventScope {
                                        while (isActive) {
                                            val event = awaitPointerEvent()
                                            event.changes.forEach { change ->
                                                viewModel.processIntent(
                                                    SettingsTicketsIntent.ChangeCoordinatesQRcode(
                                                        change.positionChange().x.toInt(),
                                                        change.positionChange().y.toInt()
                                                    )
                                                )
                                            }
                                            actioTscPrinter(
                                                viewModel.state.x,
                                                viewModel.state.y,
                                                viewModel.state.heightTicketMM,
                                                viewModel.state.widthTicketMM
                                            )
                                        }
                                    }
                                }, contentAlignment = Alignment.TopStart
                        ) {
                            if ((viewModel.state.widthTicketMM * 9.15) > barcode.width &&
                                (viewModel.state.heightTicketMM * 9.15) > barcode.height
                            ) {
                                Column(
                                    modifier = Modifier.offset(
                                        viewModel.state.x.dp,
                                        viewModel.state.y.dp
                                    )
                                        .align(Alignment.TopStart),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Image(
                                        bitmap = barcode.asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width((barcode.width / 2).dp)
                                            .height((barcode.height / 2).dp)
                                    )




                                    Image(
                                        bitmap = text.asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width((text.width / 10).dp)
                                            .height((text.height / 4).dp)
                                    )

                                }

                            } else {
                                Column {
                                    Image(
                                        painter = painterResource(Res.drawable.error),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(150.dp)
                                            .height(150.dp)
                                    )

                                    Spacer(Modifier.size(30.dp))
                                    Text(
                                        "Штрих код больше чем итекетка",
                                        Modifier.align(Alignment.CenterHorizontally),
                                        fontSize = 12.sp
                                    )
                                }
                            }
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
                        value = if (viewModel.state.widthTicketMM == -1) {
                            ""
                        } else {
                            viewModel.state.widthTicketMM.toString()
                        },
                        onValueChange = {

                            viewModel.processIntent(
                                SettingsTicketsIntent.SetCoordinatesQRcode(
                                    widht = it.toInt(),
                                    barcode = barcode,
                                    text = text,
                                    height = viewModel.state.heightTicketMM,
                                    heightQrCode = heightQrCode,

                                    // widht = viewModel.state.widthTicketMM,
                                    actioTscPrinter = actioTscPrinter
                                ))

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
                        value = if (viewModel.state.heightTicketMM == -1) {
                            ""
                        } else {
                            viewModel.state.heightTicketMM.toString()
                        },
                        onValueChange = {
                            viewModel.processIntent(
                                SettingsTicketsIntent.SetCoordinatesQRcode(
                                    height = it.toInt(),
                                    barcode = barcode,
                                    text = text,
                                    widht = viewModel.state.widthTicketMM,
                                    heightQrCode = heightQrCode,
                                    actioTscPrinter = actioTscPrinter
                                ))
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
                            { }
                    )
                }
            }
        }
    }
}


