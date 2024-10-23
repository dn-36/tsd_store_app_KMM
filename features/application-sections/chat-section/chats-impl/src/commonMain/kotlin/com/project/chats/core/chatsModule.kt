package com.project.chats.core

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.project.chats.ChatScreensApi
import com.project.chats.screens.chats.datasource.ChatRepositoryImpl
import com.project.chats.screens.chats.domain.ChatsRepositoryApi
import com.project.chats.screens.chats.domain.GetListChatsUseCase
import com.project.network.chats_network.ChatsApi
import com.project.chats.screens.chats.viewmodel.ChatsViewModel
import com.project.chats.screens.dialog.datasource.DialogRepositoryImpl
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.GetListMessagesUseCase
import com.project.chats.screens.dialog.domain.ReadMessegeUseCase
import com.project.chats.screens.dialog.domain.SendMessageUseCase
import com.project.chats.screens.dialog.viewmodel.DialogViewModel
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
         ChatRepositoryImpl(get(),get()) as  ChatsRepositoryApi
     }
     factory {
          ChatsApi()
     }
     factory {
          DialogViewModel(get(),get(),get())
     }
     factory {
          ReadMessegeUseCase(get())
     }
     factory {
          SendMessageUseCase(get())
     }

     factory {
          GetListMessagesUseCase(get())
     }
     factory {
         DialogRepositoryImpl(get(),get()) as DialogRepositoryApi
     }

}