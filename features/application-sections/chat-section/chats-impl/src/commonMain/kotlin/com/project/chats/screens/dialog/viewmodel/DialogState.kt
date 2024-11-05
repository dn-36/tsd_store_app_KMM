package com.project.chats.screens.dialog.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.ReplyMessage

data class DialogState(
    var titleChats: String = "",
    val listImages:List<ImageBitmap>? = null,
    val listMessage:List<Message> = mutableListOf(),
    val replyMessage: ReplyMessage? = null,
)
