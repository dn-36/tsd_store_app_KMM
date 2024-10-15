package com.project.chats.screens.dialog.domain.models


data class Message(
   val text:String,
   val name:String,
   val time:String,
   val date:String,
   val whoseMessage: WhoseMessage,
)

enum class WhoseMessage{
   INTERLOCUTOR,YOU
}