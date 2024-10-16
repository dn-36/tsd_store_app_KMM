package com.project.chats.screens.dialog.domain

class SendMessageUseCase(
    private val dialogRepositoryApi:DialogRepositoryApi
) {
    suspend fun execute(text:String,ui:String){
     dialogRepositoryApi.sendMessege(text,ui)
    }
}