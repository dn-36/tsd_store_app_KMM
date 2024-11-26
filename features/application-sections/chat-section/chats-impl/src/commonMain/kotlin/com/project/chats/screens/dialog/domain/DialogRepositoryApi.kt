package com.project.chats.screens.dialog.domain

import com.project.chats.screens.dialog.domain.models.Message
import kotlinx.coroutines.CoroutineScope

interface DialogRepositoryApi {
        companion object{
            val ERROR = "ERROR"
        }

    suspend fun getListMessages(
        uiChats:String,
        userToken:String,
    ):List<Message>?

    suspend fun sendMessege(
        text:String,
        feedbackUi:String?,
        uiChat:String,
        imageBiteMap:String?,
        userToken:String
    ):String

    suspend fun readMessege(uiMessage:String)
    suspend fun deleteMeddege(uiMessage:String)
    suspend fun getToken():String
}