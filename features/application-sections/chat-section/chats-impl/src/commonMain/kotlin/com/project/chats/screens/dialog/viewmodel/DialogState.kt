package com.project.chats.screens.dialog.viewmodel

import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.ReplyMessage

data class DialogState(
    var titleChats: String = "",
    val listMessage:List<Message> = listOf(),
    val replyMessage: ReplyMessage? = null,
)
