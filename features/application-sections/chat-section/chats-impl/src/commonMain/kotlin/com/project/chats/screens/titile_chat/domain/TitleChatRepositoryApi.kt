package com.project.chats.screens.titile_chat.domain

import com.project.chats.screens.select_contact.domain.User

interface TitleChatRepositoryApi {

    suspend fun creatChat(
        titleChat:String,
        imageBase64:String?,
        projectId:Int,
        list:List<User>,
        token:String
    )
    suspend fun getToken():String
}