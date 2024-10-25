package com.project.chats.screens.dialog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.viewmodel.DialogIntents
import com.project.chats.screens.dialog.viewmodel.DialogViewModel
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.core_app.network_base_screen.NetworkViewModel
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.add_photo
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.dots
import project.core.resources.paperclip
import project.core.resources.user_chats

class DialogComponentScreen(
    private val uiChats:String,
    private val titleChat:String,
    private val urlIcon:String?,
    private val countNewMessage:Int,
    override val viewModel: DialogViewModel
    //private val viewModel: DialogViewModel,
):NetworkComponent {

    @Composable
    override fun Component() {

        val scope = rememberCoroutineScope()

        val images = remember { mutableStateListOf<ImageBitmap>() }

        val multipleImagePicker = rememberImagePickerLauncher(
            scope = scope,
            selectionMode = SelectionMode.Multiple(),
            onResult = { byteArrays ->
                images += byteArrays.map {
                    it.toImageBitmap()
                }
            }
        )

        val listState = rememberLazyListState()


        LaunchedEffect(this.viewModel.state.listMessage.size) {
            listState.scrollToItem(this@DialogComponentScreen.viewModel.state.listMessage.size)
        }

        this.viewModel.processIntent(DialogIntents.SetScreen(uiChats, scope))

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.back),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { this@DialogComponentScreen.viewModel.processIntent(DialogIntents.Back) }
                        )

                        Spacer(modifier = Modifier.width(15.dp))
                        if (urlIcon.isNullOrBlank()) {
                            Image(
                                painter = painterResource(Res.drawable.user_chats),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        } else {
                            CoilImage(
                                imageModel = { urlIcon },
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(titleChat, fontSize = 20.sp)
                    }

                    Image(
                        painter = painterResource(Res.drawable.dots),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { this@DialogComponentScreen.viewModel.processIntent(DialogIntents.HistoryFiles) }
                    )

                }

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.94f),
                    verticalArrangement = Arrangement.Bottom
                ) {


                    items(this@DialogComponentScreen.viewModel.state.listMessage) { item ->
                        if (item.isShowDate) {
                            MessageDataComponent(item.time)
                        }

                        MessageComponent(
                            Message(
                                item.text,
                                item.name,
                                item.date,
                                item.time,
                                item.url_icon,
                                item.whoseMessage,
                                statusMessage = item.statusMessage
                            ),
                            {
                                this@DialogComponentScreen.viewModel.sendMessageUseCase(
                                    this@DialogComponentScreen.viewModel.state.listMessage.last().text,
                                    uiChats,
                                    scope
                                )
                            }
                        )
                    }
                }
            }
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                if (images.size != 0) {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(images) { item ->
                            Box(modifier = Modifier.size(95.dp)) {
                                Image(
                                    modifier = Modifier.padding(10.dp)
                                        .size(90.dp),
                                    bitmap = item,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Image(
                                    painterResource(Res.drawable.cancel),
                                    contentDescription = null,
                                    modifier = Modifier.size(10.dp).align(Alignment.TopEnd)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })
                                        { images.remove(item) })
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {


                            BasicTextField(
                                value = this@DialogComponentScreen.viewModel.state.titleChats,
                                onValueChange = {
                                    this@DialogComponentScreen.viewModel.state = this@DialogComponentScreen.viewModel.state.copy(
                                        titleChats = it
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.65f)
                                    .heightIn(min = 33.dp, max = 150.dp),
                                textStyle = TextStyle(fontSize = 18.sp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            )

                            Image(
                                painter = painterResource(Res.drawable.add_photo),
                                contentDescription = null,
                                modifier = Modifier.padding(bottom = 15.dp, top = 8.dp)
                                    .size(22.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { multipleImagePicker.launch() }
                            )

                            Spacer(modifier = Modifier.width(7.dp))

                            Image(
                                painter = painterResource(Res.drawable.paperclip),
                                contentDescription = null,
                                modifier = Modifier.padding(bottom = 15.dp, top = 8.dp)
                                    .size(22.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { }
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Image(
                                painter = painterResource(Res.drawable.back),
                                contentDescription = null,
                                modifier = Modifier.padding(bottom = 15.dp, top = 8.dp)
                                    .size(25.dp)
                                    .graphicsLayer(rotationZ = 180f).clickable {

                                        this@DialogComponentScreen.viewModel.sendMessageUseCase(
                                            this@DialogComponentScreen.viewModel.state.titleChats,
                                            uiChats,
                                            scope
                                        )
                                        this@DialogComponentScreen.viewModel.state = this@DialogComponentScreen.viewModel.state.copy(
                                            titleChats = ""
                                        )
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}
