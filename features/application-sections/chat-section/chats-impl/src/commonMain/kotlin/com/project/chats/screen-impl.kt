package com.project.chats

import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.screen.ChatsScreena

class ChatScreensImpl: ChatScreensApi {
    override fun ChatsScreen(): Screen {
        return ChatsScreena
    }
}