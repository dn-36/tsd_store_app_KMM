package com.project.chats.core

import com.project.chats.ChatScreensApi
import com.project.chats.screens.chats.datasource.ChatRepositoryImpl
import com.project.chats.screens.chats.domain.ChatsRepositoryApi
import com.project.chats.screens.chats.domain.DeleteChatUseCase
import com.project.chats.screens.chats.domain.GetListChatsUseCase
import com.project.network.chats_network.ChatsApi
import com.project.chats.screens.chats.viewmodel.ChatsViewModel
import com.project.chats.screens.dialog.datasource.DialogRepositoryImpl
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.GetListMessagesUseCase
import com.project.chats.screens.dialog.domain.ReadMessegeUseCase
import com.project.chats.screens.dialog.domain.SendMessageUseCase
import com.project.chats.screens.dialog.viewmodel.DialogViewModel
import com.project.chats.screens.select_contact.datasource.SelectContactRepositoryImpl
import com.project.chats.screens.select_contact.domain.GetUsersOrganizationUseCase
import com.project.chats.screens.select_contact.domain.SelectContactRepositoryApi
import com.project.chats.screens.select_contact.viewmodel.SelectContactsViewModel
import com.project.chats.screens.titile_chat.datasource.TitleChatRepositoryImpl
import com.project.chats.screens.titile_chat.domain.CreateChatUseCase
import com.project.chats.screens.titile_chat.domain.TitleChatRepositoryApi
import com.project.chats.screens.titile_chat.viewmodel.TitleChatViewModel
import com.project.network.users_network.UsersClient
import org.koin.dsl.module

val chatsModule = module{
     factory{
          ChatScreensImpl() as ChatScreensApi
     }
     factory {
          ChatsViewModel(get(),get())
     }
     factory {
          DeleteChatUseCase(get())
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
     factory {
          SelectContactsViewModel(get())
     }
     factory {
          GetUsersOrganizationUseCase(get())
     }
     factory {
         SelectContactRepositoryImpl(get(),get()) as SelectContactRepositoryApi
     }
     factory {
          UsersClient()
     }
     factory {
          TitleChatViewModel(get())
     }
     factory { CreateChatUseCase(get()) }
     factory {  TitleChatRepositoryImpl(get(),get()) as TitleChatRepositoryApi}

}