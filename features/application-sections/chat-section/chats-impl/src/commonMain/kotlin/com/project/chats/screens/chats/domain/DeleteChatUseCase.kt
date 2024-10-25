package com.project.chats.screens.chats.domain

class DeleteChatUseCase( private val repositoryApi: ChatsRepositoryApi ) {
   suspend fun execute(ui:String):String{
       return repositoryApi.deleteChat(ui)
    }
}