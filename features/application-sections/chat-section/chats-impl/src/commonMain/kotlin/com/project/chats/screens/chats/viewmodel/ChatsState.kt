package com.project.chats.screens.chats.viewmodel

import com.project.chats.screens.chats.domain.models.ChatsModel

data class ChatsState(
    val chats:List<ChatsModel> = listOf()
)
