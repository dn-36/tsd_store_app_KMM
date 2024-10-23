package com.project.chats.screens.dialog.domain.models


data class Message(
    val text:String,
    val name:String,
    val date:String,
    val time:String,
    val url_icon:String?,
    val whoseMessage: WhoseMessage,
    var isShowDate:Boolean = false,
    val isReaded:Boolean = false,
    var statusMessage:StatusMessage = StatusMessage.IS_SECCUESS
)

enum class WhoseMessage{
   INTERLOCUTOR,YOU
}
enum class StatusMessage{
    IS_LOADING,IS_SECCUESS,IS_ERROR,IS_READED
}