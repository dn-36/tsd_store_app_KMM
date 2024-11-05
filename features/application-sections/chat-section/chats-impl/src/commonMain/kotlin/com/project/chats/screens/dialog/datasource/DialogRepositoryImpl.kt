package com.project.chats.screens.dialog.datasource

import com.project.chats.core.Utils
import com.project.chats.screens.dialog.domain.DialogRepositoryApi
import com.project.chats.screens.dialog.domain.DialogRepositoryApi.Companion.ERROR
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.ReplyMessage
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi
import util.NetworkError
import util.Result

class DialogRepositoryImpl(
    private val chatApi:ChatsApi,
    private val profileSavedDate: SharedPrefsApi,
    ):DialogRepositoryApi {

    override suspend fun getListMessages(uiChats: String,userToken:String): List<Message>? {
        return chatApi.getListMassengers(uiChats).messages?.data?.map {
        val replyMessage:ReplyMessage? = if(it.feedback != null){
            ReplyMessage(
             "Reply message",
             it.feedback?.text?:"",
                 null
         )
        } else{
             null
        }

            Message(
                it.text ?: "",
                it.user?.name?:"",
                Utils.parseDateTimeManually (it?.created_at?:"").time ,
                Utils.parseDateTimeManually (it?.created_at?:"").date ,
                if(it?.user?.phone == profileSavedDate.getCurrentNumber()?:"") WhoseMessage.YOU
                else WhoseMessage.INTERLOCUTOR,
                ui = it.ui?:"",
                isReaded = it.status_view == 1,
                answerMessage = replyMessage,
                url_icon = if(it.image != null)"https://delta.online/storage/" + it.image else null
            )
        }
    }



    override suspend fun sendMessege(
        text: String,
        feedbackUi: String?,
        uiChat: String,
        imageBiteMap : String?,
        userToken: String
    ):String {
       val result:Result<String,NetworkError> = chatApi.sendMessage(
           uiChat,
           feedbackUi?:"",
           text,
           imageBiteMap,
           listOf(),
           listOf()
       )
       return if(result is Result.Success){
          result.data
       }else{
         ERROR
       }
    }


    override suspend fun readMessege(ui: String) {
    chatApi.readAllMesanger(ui,profileSavedDate.getCurrentNumber()?:"")
    }

    override suspend fun deleteMeddege(ui: String) {

    }

    override suspend fun getToken(): String = profileSavedDate.getToken()?:""



}