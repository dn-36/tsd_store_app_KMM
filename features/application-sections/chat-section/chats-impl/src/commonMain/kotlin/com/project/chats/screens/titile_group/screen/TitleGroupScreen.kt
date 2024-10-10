package org.example.project.nika_screens_chats.title_group_feature.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap

import org.example.project.nika_screens_chats.title_group_feature.viewmodel.TitleGroupIntents
import org.example.project.nika_screens_chats.title_group_feature.viewmodel.TitleGroupViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.user
import project.core.resources.user_chats


class TitleGroupScreen : Screen {

    private val vm = TitleGroupViewModel()

    @Composable
    override fun Content() {

        //
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
        //

        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(0.95f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(images.size == 0) {
                    Image(
                        painter = painterResource(Res.drawable.user_chats),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        {
                            if (images.size > 0) {
                                images.removeLast()
                            }
                            multipleImagePicker.launch()
                        }
                    )
                }
                else {
                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        bitmap = images[0],
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Column {
                    TextField(
                        value = vm.titleGroupState.text,
                        onValueChange = { vm.titleGroupState = vm.titleGroupState.copy(
                            text = it
                        )
                                        },
                        label = {
                            Text(
                                "Название чата", fontSize = 15.sp,
                                color = Color(0xFFBCBBBB)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color.Transparent
                        ),
                        textStyle = TextStyle(fontSize = 15.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(0.9f).align(Alignment.Center)
                                .height(1.dp)
                                .background(Color.LightGray)
                        )
                    }
                }
            }
            Image(
                painter = painterResource(Res.drawable.back), contentDescription = null,
                modifier = Modifier.padding(25.dp).size(60.dp).align(Alignment.BottomEnd).clickable(
                    indication = null, // Отключение эффекта затемнения
                    interactionSource = remember { MutableInteractionSource() })
                { vm.processIntent(TitleGroupIntents.Ready) }
            )
        }
    }
}