package com.project.chats.screens.dialog.datasource

import com.project.chats.core.Utils
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi

class DialogRepositoryImpl(
    private val chatApi:ChatsApi,
    private val profileSavedDate: SharedPrefsApi
):DialogRepositoryApi {


    override suspend fun getListMessages(uiChats: String): List<Message> {
        return chatApi.getListMassengers(uiChats).messages.data.map {
            println(it.user.phone)
            println("zzzz"+profileSavedDate.getCurrentNumber()?:""+"zzzz")
            Message(
                it.text ?: "",
                it.user.name,
                Utils.parseDateTimeManually (it.created_at).time ,
                Utils.parseDateTimeManually (it.created_at).date ,
                if(it.user.phone == profileSavedDate.getCurrentNumber()?:"")WhoseMessage.YOU
                else WhoseMessage.INTERLOCUTOR
            )
        }
    }

    override suspend fun sendMessege(ui: String) {

    }

    override suspend fun readMessege(ui: String) {

    }

    override suspend fun deleteMeddege(ui: String) {

    }


}