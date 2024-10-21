package com.project.chats.screens.dialog.domain

import com.project.chats.screens.dialog.domain.models.Message

interface DialogRepositoryApi {
        companion object{
            val ERROR = "ERROR"
        }

    suspend fun getListMessages(uiChats:String,userToken:String):List<Message>
    suspend fun sendMessege(text:String,uiMessage:String,userToken:String):String
   // suspend fun getToken():String
    suspend fun readMessege(uiMessage:String)
    suspend fun deleteMeddege(uiMessage:String)
    suspend fun getToken():String
}