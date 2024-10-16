package com.project.chats.screens.dialog.datasource

import com.project.chats.core.Utils
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.`local-storage`.`profile-storage`.ProfileValueStorageApi
import com.project.network.chats_network.ChatsApi
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class DialogRepositoryImpl(
    private val chatApi:ChatsApi,
    private val profileSavedDate: ProfileValueStorageApi
):DialogRepositoryApi {

    override suspend fun getListMessages(uiChats: String): List<Message> {
        return chatApi.getListMassengers(uiChats).messages?.data?.map {
            Message(
                it.text ?: "",
                it.user?.name?:"",
                Utils.parseDateTimeManually (it?.created_at?:"").time ,
                Utils.parseDateTimeManually (it?.created_at?:"").date ,
                if(it?.user?.phone == profileSavedDate.getCurrentNumber()?:"")WhoseMessage.YOU
                else WhoseMessage.INTERLOCUTOR
            )
        }?: listOf()
    }

    override suspend fun sendMessege(
        text:String,
        chat_ui:String
    ) {
        chatApi.sendMessage(
            chat_ui,
            "",
            text,
            null,
            listOf(),
            listOf()
        )
       /*chatApi.sendMessage(
            "U5OG8ELF" ,
            "",
            "qqqqq_111_skksks",
            null,
            listOf(),
            listOf()
        )*/
    }

    override suspend fun readMessege(ui: String) {

    }

    override suspend fun deleteMeddege(ui: String) {

    }


}