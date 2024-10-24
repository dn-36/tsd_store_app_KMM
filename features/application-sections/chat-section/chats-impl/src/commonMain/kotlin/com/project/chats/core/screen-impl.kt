package com.project.chats.core

import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.ChatScreensApi
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.select_contact.screen.SelectContactsScreen
import org.example.project.nika_screens_chats.history_files_feature.screen.HistoryFilesScreen

class ChatScreensImpl: ChatScreensApi {
    override fun chatsScreen(): Screen {
        return ChatsScreen()
    }
    override fun historyFilesScreen(): Screen {
        return HistoryFilesScreen()
    }
    override fun selectContactsScreen(): Screen {
        return SelectContactsScreen()
    }


}