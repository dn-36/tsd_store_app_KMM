package com.project.chats.screens.dialog.datasource

import com.project.chats.core.Utils
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi
import product_network.model.LocalStore
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class DialogRepositoryImpl(
    private val chatApi:ChatsApi,
    private val profileSavedDate: SharedPrefsApi,

    ):DialogRepositoryApi {

    override suspend fun getListMessages(uiChats: String,userToken:String): List<Message> {
        return chatApi.getListMassengers(uiChats).messages?.data?.map {
            Message(
                it.text ?: "",
                it.user?.name?:"",
                Utils.parseDateTimeManually (it?.created_at?:"").time ,
                Utils.parseDateTimeManually (it?.created_at?:"").date ,
                it.image,
                if(it?.user?.phone == profileSavedDate.getCurrentNumber()?:"") WhoseMessage.YOU
                else WhoseMessage.INTERLOCUTOR
            )
        }?: listOf()
    }

    override suspend fun sendMessege(
        text:String,
        chat_ui:String,
        userToken:String
    ) {
        chatApi.sendMessage(
            chat_ui,
            "",
            text,
            null,
            listOf(),
            listOf()
        )

    }



   // override suspend fun getToken(): String = chatApi

    override suspend fun readMessege(ui: String) {

    }

    override suspend fun deleteMeddege(ui: String) {

    }

    override suspend fun getToken(): String = profileSavedDate.getToken()?:""



}