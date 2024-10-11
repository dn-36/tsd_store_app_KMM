package com.project.chats

import com.project.chats.screens.chats.datasource.ChatRepositoryImpl
import com.project.chats.screens.chats.domain.ChatsRepositoryApi
import com.project.chats.screens.chats.domain.GetListChatsUseCase
import com.project.network.chats_network.ChatsApi
import org.example.project.nika_screens_chats.list_dialog_feature.viewmodel.ChatsViewModel
import org.koin.dsl.module

val chatsModule = module{
     factory{
          ChatScreensImpl() as ChatScreensApi
     }
     factory {
          ChatsViewModel(get())
     }
     factory {
          GetListChatsUseCase(get())
     }
     factory {
         ChatRepositoryImpl(get()) as  ChatsRepositoryApi
     }
     factory {
          ChatsApi()
     }
}