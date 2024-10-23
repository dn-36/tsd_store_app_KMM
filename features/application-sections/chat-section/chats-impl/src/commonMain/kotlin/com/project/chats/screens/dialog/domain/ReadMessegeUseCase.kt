package com.project.chats.screens.dialog.domain

class ReadMessegeUseCase(private val repositoryApi: DialogRepositoryApi) {
    suspend fun execute(uiChat:String){

        println()
        repositoryApi.readMessege(uiChat)
    }
}