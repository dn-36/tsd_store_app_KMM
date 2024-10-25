package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import com.preat.peekaboo.image.picker.toImageBitmap
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionIntents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import project.core.resources.Res
import project.core.resources.add_photo
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.ready
import qrscanner.QrCodeScanner
import qrscanner.QrScanner
import qrscanner.scanImage


class ScannerComponent (

    val onClickAdd: ( name: String ) -> Unit,

    val onClickCansel: ( ) -> Unit,

    ) {

    @Composable
    fun QrScannerView() {

        var qrCodeURL by remember { mutableStateOf("") }

        val coroutineScope = rememberCoroutineScope()

        //

        var images = remember { mutableStateOf<ImageBitmap?>(null) }

        var ready = remember { mutableStateOf<Boolean>(false) }

        var showCamera by remember { mutableStateOf(true) }

        val cameraState = rememberPeekabooCameraState(
            onCapture = { bytes ->
                bytes?.toImageBitmap()?.let {
                    images.value = it
                    ready.value = true
                    showCamera = false
                }
            }
        )

        //

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {


            Row( modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.back), contentDescription = null,
                    modifier = Modifier.size(20.dp).clickable(
                        indication = null, // Отключение эффекта затемнения
                        interactionSource = remember { MutableInteractionSource() })
                    { onClickCansel() }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text("Приход", color = Color.Black, fontSize = 20.sp)

            }

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(300.dp)
                        .height(300.dp)
                        .clipToBounds()
                        .border(2.dp, Color.Gray, RoundedCornerShape(size = 0.dp)),
                    contentAlignment = Alignment.Center
                ) {

                    if (showCamera) {

                        Box  {
                            PeekabooCamera(
                                modifier = Modifier.width(300.dp)
                                    .height(300.dp),
                                state = cameraState
                            )
                            IconButton(
                                modifier = Modifier
                                    .align(alignment = Alignment.BottomCenter)
                                    .padding(bottom = 10.dp),
                                onClick = {

                                    cameraState.capture()

                                }
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(70.dp))
                                        .height(30.dp)
                                        .fillMaxWidth(0.5f)
                                        .background(Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Готово")
                                }

                                /*Image(painterResource(Res.drawable.ready), contentDescription = null,

                                    modifier = Modifier.size(20.dp))*/

                                /*Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = null
                                )*/
                            }
                        }


                    } else {

                        if(ready.value) {

                            showCamera = true

                            coroutineScope.launch {

                                scanImage(
                                    images.value,
                                    onCompletion = { answer ->

                                        qrCodeURL = answer

                                    },

                                    onFailure = { error ->

                                        coroutineScope.launch {

                                            qrCodeURL = "ошибка"

                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                if (qrCodeURL.isNotEmpty()) {

                    Column ( modifier = Modifier.align(Alignment.BottomCenter)
                        .padding( 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){

                        Text(
                            text = "Результат : ${qrCodeURL} ",
                            fontSize = 20.sp,
                            color = Color.Black,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(25.dp))


                        if ( qrCodeURL != "ошибка" ) {
                            Button(
                                onClick = { onClickAdd( qrCodeURL ) },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(70.dp))
                                    .height(40.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "Добавить")
                            }
                        }

                    }

                }
        }
    }
}

