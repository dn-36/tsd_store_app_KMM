package com.project.chats.core

import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.ChatScreensApi
import com.project.chats.screens.chats.screen.ChatsScreen
import org.example.project.nika_screens_chats.add_chat_feature.screen.SelectContactsScreen
import org.example.project.nika_screens_chats.history_files_feature.screen.HistoryFilesScreen
import org.example.project.nika_screens_chats.title_group_feature.screen.TitleGroupScreen

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
   /* override fun dialogScreen(): Screen {
        return DialogScreen()
    }*/
    override fun titleGroupScreen(): Screen {
        return TitleGroupScreen()
    }
}