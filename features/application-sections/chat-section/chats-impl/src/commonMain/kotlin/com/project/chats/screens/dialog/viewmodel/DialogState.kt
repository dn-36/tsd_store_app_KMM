package com.project.chats.screens.dialog.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import com.project.chats.screens.dialog.domain.models.Message

data class DialogState(

    var titleChats: String = "",

    val listMessage:List<Message> = listOf(),

    val isVisibilitySelectFileComponent: MutableState<Float> = mutableStateOf(0f),

    val listImages: List<ImageBitmap> = emptyList()

)
