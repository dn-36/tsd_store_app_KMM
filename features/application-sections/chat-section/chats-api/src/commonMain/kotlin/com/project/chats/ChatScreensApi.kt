package com.project.chats

import cafe.adriel.voyager.core.screen.Screen
import kotlin.jvm.Transient

interface ChatScreensApi {
    fun chatsScreen():Screen
     fun selectContactsScreen():Screen
}