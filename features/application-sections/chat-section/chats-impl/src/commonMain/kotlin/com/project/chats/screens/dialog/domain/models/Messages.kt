package com.project.chats.screens.dialog.domain.models

import androidx.compose.ui.graphics.ImageBitmap


data class Message(
    val text:String,
    val name:String,
    val date:String,
    val time:String,
    val whoseMessage: WhoseMessage,
    var isShowDate:Boolean = false,
    val isReaded:Boolean = false,
    val ui:String,
    var answerMessage:ReplyMessage? = null,
    var statusMessage:StatusMessage = StatusMessage.IS_SECCUESS,
    var url_icon:String? = null

)

enum class WhoseMessage{
   INTERLOCUTOR,YOU
}
enum class StatusMessage{
    IS_LOADING,IS_SECCUESS,IS_ERROR,IS_READED
}
data class ReplyMessage(
    val name:String,
    val text:String,
    val ui:String?
)