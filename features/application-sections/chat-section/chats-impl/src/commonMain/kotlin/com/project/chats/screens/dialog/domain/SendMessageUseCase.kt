package com.project.chats.screens.dialog.domain

class SendMessageUseCase(
    private val dialogRepositoryApi:DialogRepositoryApi
) {
    suspend fun execute(text:String,ui:String,onSeccuess:(String)->Unit,
                        onError:(String)->Unit){

     dialogRepositoryApi.sendMessege(
         text,
         ui,
         dialogRepositoryApi.getToken(),
         onSeccuess,
         onError
     )
    }
}