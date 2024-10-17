package com.project.chats.screens.chats.datasource

import com.project.chats.core.Utils
import com.project.chats.screens.chats.domain.ChatsRepositoryApi
import com.project.chats.screens.chats.domain.models.ChatsModel
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi

class ChatRepositoryImpl(
    private val chatApi:ChatsApi,
    private val sharedPrefsApi: SharedPrefsApi
    ):ChatsRepositoryApi {
    override suspend fun getListChats():List<ChatsModel>{
        chatApi.init(sharedPrefsApi.getToken()?:"")
       val listChats = chatApi.getChats().map {
        //   println(">>>>>>>${it.ui}>>>>>>>>>")
           ChatsModel(
               it?.name?:"",
               it?.message?:"",
               Utils.parseDateTimeManually(it.created_at?:""),
               it?.ui?:""
           )
       }?: listOf()
        return Utils.sortByNearestDate(listChats)
    }


    }
