package com.project.chats.screens.dialog.components

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.add_photo
import project.core.resources.camera_2
import project.core.resources.cancel
import project.core.resources.copy
import project.core.resources.gallery
import project.core.resources.gallery_2
import project.core.resources.paperclip

class SelectFileForSendComponent (

    val onClickCansel:() -> Unit,

    val onClickAddNewPhoto:( images: List<ImageBitmap> ) -> Unit

    ) {

    @Composable
    fun Content() {

        val scope = rememberCoroutineScope()

        val images = remember { mutableStateListOf<ImageBitmap>() }

        val multipleImagePicker = rememberImagePickerLauncher(
            scope = scope,
            selectionMode = SelectionMode.Multiple(),
            onResult = { byteArrays ->

                images += byteArrays.map {

                    it.toImageBitmap()

                }

                onClickAddNewPhoto( images )

            }
        )

        var showCamera by remember { mutableStateOf(false) }

        val cameraState = rememberPeekabooCameraState(

            onCapture = { bytes ->

                bytes?.toImageBitmap()?.let {

                    images += it

                    showCamera = false

                    onClickAddNewPhoto( images )
                }
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {

                Column( modifier = Modifier.padding(16.dp).fillMaxHeight(),

                    horizontalAlignment = Alignment.CenterHorizontally ) {

                    Row ( modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.End ) {

                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                { onClickCansel() })

                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    Row ( modifier = Modifier.fillMaxWidth(0.9f),

                        horizontalArrangement = Arrangement.SpaceBetween) {

                        Image(
                            painterResource(Res.drawable.camera_2), contentDescription = null,
                            modifier = Modifier.size(50.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })

                            { showCamera = true }
                        )

                        Image(
                            painterResource(Res.drawable.gallery_2), contentDescription = null,
                            modifier = Modifier.size(50.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })

                            { multipleImagePicker.launch() }
                        )

                        Image(
                            painterResource(Res.drawable.copy), contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )

                    }
                }
            }
            /*Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.0f)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ){

            }*/
            if (showCamera) {

                Box  {
                    PeekabooCamera(
                        modifier = Modifier.fillMaxSize(),
                        state = cameraState
                    )
                    IconButton(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .padding(bottom = 30.dp),
                        onClick = {

                            cameraState.capture()

                        }
                    ) {

                        Box(
                            modifier = Modifier

                                .clip(CircleShape)

                                .size(70.dp)

                                .border(width = 3.dp, color = Color.White, shape = CircleShape),

                            contentAlignment = Alignment.Center
                        ) {

                        }
                    }

                    IconButton(
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .padding( 15.dp),
                        onClick = {

                            cameraState.capture()

                        }
                    ) {

                        Box( modifier = Modifier.clip ( CircleShape ). size(30.dp)

                            .background(Color.White),

                            contentAlignment = Alignment.Center

                            ) {

                            Image(painter = painterResource(Res.drawable.cancel),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })

                                    { showCamera = false })

                        }

                    }

                }


            }

            if ( showCamera == false ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.033f)
                        .background(Color.White)
                        .align(Alignment.BottomCenter)
                ) {

                }

            }

        }

    }

}