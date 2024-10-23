package com.project.chats.screens.dialog.screen

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
import cafe.adriel.voyager.core.screen.Screen
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.project.chats.screens.dialog.components.DialogComponentScreen
import com.project.chats.screens.dialog.components.MessageComponent
import com.project.chats.screens.dialog.components.MessageDataComponent
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.viewmodel.DialogIntents
import com.project.core_app.network_base_screen.NetworkScreen
import com.project.core_app.network_base_screen.NetworkViewModel
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.add_photo
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.dots
import project.core.resources.paperclip
import project.core.resources.user_chats


class DialogScreen(
    private val uiChats:String,
    private val titleChat:String,
    private val urlIcon:String?,
    private val countNewMessage:Int
) : NetworkScreen(

    DialogComponentScreen(
        uiChats,titleChat,urlIcon,countNewMessage, getKoin().get()
    ),
)




