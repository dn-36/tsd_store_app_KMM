package com.project.chats.screens.chats.domain

import com.project.chats.screens.chats.domain.models.ChatsModel

class GetListChatsUseCase(val repository: ChatsRepositoryApi) {

   suspend fun execute():List<ChatsModel>{
       return repository.getListChats()
   }


        }
