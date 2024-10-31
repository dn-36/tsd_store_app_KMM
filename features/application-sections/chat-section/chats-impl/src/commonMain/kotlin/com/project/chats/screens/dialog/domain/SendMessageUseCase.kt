package com.project.chats.screens.dialog.domain

class SendMessageUseCase(
    private val dialogRepositoryApi:DialogRepositoryApi
) {
    val ERROR = DialogRepositoryApi.ERROR

    suspend fun execute(text:String,feedbackUi:String?,ui:String):String =
        dialogRepositoryApi.sendMessege(text,feedbackUi,ui,dialogRepositoryApi.getToken())
    }
