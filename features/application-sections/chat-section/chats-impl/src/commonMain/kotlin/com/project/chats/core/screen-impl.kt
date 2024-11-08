package com.project.chats.core

import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.ChatScreensApi
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.select_contact.screen.SelectContactsScreen
import com.project.chats.screens.history_files.screen.HistoryFilesScreen

class ChatScreensImpl: ChatScreensApi {
    override fun chatsScreen(): Screen {
        return ChatsScreen()
    }


    override fun selectContactsScreen(): Screen {
        return SelectContactsScreen()
    }


}