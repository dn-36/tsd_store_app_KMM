package com.project.chats.screens.dialog.domain

import com.project.chats.screens.dialog.domain.models.Message

interface DialogRepositoryApi {
    suspend fun getListMessages(uiChats:String):List<Message>
    suspend fun sendMessege(text:String,uiMessage:String)
   // suspend fun getToken():String
    suspend fun readMessege(uiMessage:String)
    suspend fun deleteMeddege(uiMessage:String)
}