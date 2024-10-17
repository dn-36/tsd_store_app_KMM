package com.project.chats.screens.dialog.domain

import com.project.chats.screens.dialog.domain.models.Message

class GetListMessagesUseCase(private val dialogRepositoryApi:DialogRepositoryApi){
   suspend fun execute(uiChats:String):List<Message>{
        return dialogRepositoryApi.getListMessages(uiChats,dialogRepositoryApi.getToken())
    }
}



