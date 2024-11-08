package com.project.chats.screens.dialog.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.ReplyMessage

data class DialogState(
    val titleChats: String = "",
    val isShowSelectMessage:Boolean = false,
    val isShowDeleteDialog:Boolean = false,
    val listImages:List<ImageBitmap> = listOf(),
    val listMessage:List<Message> = mutableListOf(),
    val replyMessage: ReplyMessage? = null
)
