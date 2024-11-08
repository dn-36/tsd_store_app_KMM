package com.project.chats.screens.dialog.domain

class SendMessageUseCase(
    private val dialogRepositoryApi:DialogRepositoryApi
) {
    val ERROR = DialogRepositoryApi.ERROR

    suspend fun execute(text:String,feedbackUi:String?,imageBase64: String?, ui:String):String =
        dialogRepositoryApi.sendMessege(text,feedbackUi,ui,imageBase64,dialogRepositoryApi.getToken())
    }
