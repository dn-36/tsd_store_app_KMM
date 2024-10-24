package com.project.chats.screens.titile_chat.datasource

import com.project.chats.screens.select_contact.domain.User
import com.project.chats.screens.titile_chat.domain.TitleChatRepositoryApi
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.chats_network.ChatsApi


class TitleChatRepositoryImpl(
    private val client: ChatsApi,
    private val sharedPrefs:SharedPrefsApi
):TitleChatRepositoryApi {
    override suspend fun creatChat(
        titleChat: String,
        image:String?,
        projectId:Int,
        list: List<User>,
        token:String
    ) {

       client.createChat(titleChat,image,list.map{ it.number },null)
    }

    override suspend fun getToken(): String = sharedPrefs.getToken()?:""


}