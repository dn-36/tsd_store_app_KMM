package com.project.chats.screens.dialog.domain

class SendMessageUseCase(
    private val dialogRepositoryApi:DialogRepositoryApi
) {
    suspend fun execute(text:String,ui:String){
        println("----AAAA___AAAAA_aaaaa___ssss")
     dialogRepositoryApi.sendMessege(text,ui,dialogRepositoryApi.getToken())
    }
}