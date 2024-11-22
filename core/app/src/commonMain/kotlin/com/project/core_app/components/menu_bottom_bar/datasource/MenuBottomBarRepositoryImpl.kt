package com.project.core_app.components.menu_bottom_bar.datasource

import com.project.core_app.components.menu_bottom_bar.domian.MenuBottomBarRepositoryApi
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi

class MenuBottomBarRepositoryImpl(
    private val chatApi:ChatsApi,
    private val sharedPrefsApi: SharedPrefsApi
):MenuBottomBarRepositoryApi {
    override suspend fun getAllChatCountNewMessage(token:String): List<Int> {
    // var newMessage = 0
        chatApi.init(token)
        return chatApi.getListChats()?.map { it.count_new_message?:0 }?: listOf()
    }

    override suspend fun getToken(): String = sharedPrefsApi.getToken()?:""



}

/*
class MenuBottomBarRepositoryImpl(
    private val chatApi:ChatsApi,
    private val sharedPrefsApi: SharedPrefsApi
):MenuBottomBarRepositoryApi {
    override suspend fun getAllChatCountNewMessage(): Int {
     var newMessage = 0
        chatApi.init(sharedPrefsApi.getToken()?:"")
        chatApi.getListChats().forEach {
         if((it.count_new_message ?: 0) != 0){
             newMessage += it.count_new_message!!
         }
     }
     return newMessage
    }
}
 */