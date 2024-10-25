package com.project.chats.screens.chats.domain

import com.project.chats.screens.chats.domain.models.ChatsModel

interface ChatsRepositoryApi {
    suspend fun getListChats():List<ChatsModel>
    suspend fun deleteChat(ui:String):String
}