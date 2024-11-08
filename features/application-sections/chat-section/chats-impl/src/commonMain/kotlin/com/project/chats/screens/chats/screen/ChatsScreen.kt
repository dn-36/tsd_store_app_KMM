package com.project.chats.screens.chats.screen

import com.project.chats.screens.chats.components.ChatComponent
import com.project.core_app.network_base_screen.NetworkScreen

import org.koin.mp.KoinPlatform.getKoin

class ChatsScreen : NetworkScreen(
    ChatComponent(getKoin().get())
)