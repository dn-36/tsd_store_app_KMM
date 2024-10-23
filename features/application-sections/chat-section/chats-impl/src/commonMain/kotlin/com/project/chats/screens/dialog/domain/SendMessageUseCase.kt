package com.project.chats.screens.dialog.domain

class SendMessageUseCase(
    private val dialogRepositoryApi:DialogRepositoryApi
) {
    val ERROR = DialogRepositoryApi.ERROR

    suspend fun execute(text:String,ui:String):String =
        dialogRepositoryApi.sendMessege(text,ui,dialogRepositoryApi.getToken())
    }
