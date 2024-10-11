package com.project.chats.screens.chats.datasource

import com.project.chats.screens.chats.domain.ChatsRepositoryApi
import com.project.chats.screens.chats.domain.models.ChatsModel
import com.project.network.chats_network.ChatsApi

class ChatRepositoryImpl(private val chatApi:ChatsApi):ChatsRepositoryApi {
    override suspend fun getListChats() = chatApi.getChats().map {
        ChatsModel(
        it.name,
        it.message
        )
    }
}