package com.project.chats

import cafe.adriel.voyager.core.screen.Screen
import kotlin.jvm.Transient

interface ChatScreensApi {
    fun chatsScreen():Screen
    //fun dialogScreen():Screen
    fun historyFilesScreen():Screen
     fun selectContactsScreen():Screen
}