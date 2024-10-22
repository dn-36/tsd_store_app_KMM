package com.project.chats.screens.dialog.datasource

import com.project.chats.core.Utils
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.DialogRepositoryApi.Companion.ERROR
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi
import util.NetworkError
import util.Result

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
                else WhoseMessage.INTERLOCUTOR,
                isReaded = it.status_view == 1
            )
        }?: listOf()
    }

    override suspend fun sendMessege(
        text:String,
        chat_ui:String,
        userToken:String
    ):String {
       val result:Result<String,NetworkError> = chatApi.sendMessage(
           chat_ui,
           "",
           text,
           null,
           listOf(),
           listOf()
       )
       return if(result is Result.Success){
          result.data
       }else{
         ERROR
       }
    }



   // override suspend fun getToken(): String = chatApi

    override suspend fun readMessege(ui: String) {
    chatApi.readAllMesanger(ui,profileSavedDate.getCurrentNumber()?:"")
    }

    override suspend fun deleteMeddege(ui: String) {

    }

    override suspend fun getToken(): String = profileSavedDate.getToken()?:""



}