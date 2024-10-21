package com.project.chats.screens.chats.domain.models

import com.project.chats.screens.dialog.domain.models.DataEndTime

data class ChatsModel(
    val urlIconChat:String?,
    val title:String = "названия чата",
    val lastMassage:String = "последние сообщение",
    val timeEndDate:DataEndTime,
    val uiChat:String,
    val countNewMessage:Int = 0
)
