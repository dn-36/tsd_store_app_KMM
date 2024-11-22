package com.project.chats.screens.dialog.domain

import kotlinx.coroutines.CoroutineScope

class ReadMessegeUseCase(private val repositoryApi: DialogRepositoryApi) {
    suspend fun execute(uiChat:String){


        repositoryApi.readMessege(uiChat)
    }
}