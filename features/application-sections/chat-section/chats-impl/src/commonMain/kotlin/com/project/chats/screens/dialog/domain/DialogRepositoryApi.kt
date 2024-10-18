package com.project.chats.screens.dialog.domain

import com.project.chats.screens.dialog.domain.models.Message

interface DialogRepositoryApi {
    suspend fun getListMessages(uiChats:String,userToken:String):List<Message>
    suspend fun sendMessege(text:String,
                            chat_ui:String,
                            userToken:String,
                            onSeccuess:(String)->Unit,
                            onError:(String)->Unit)
   // suspend fun getToken():String
    suspend fun readMessege(uiMessage:String)
    suspend fun deleteMeddege(uiMessage:String)
    suspend fun getToken():String
}