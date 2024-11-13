package com.project.core_app.components.menu_bottom_bar.domian

interface MenuBottomBarRepositoryApi {

    suspend fun getAllChatCountNewMessage(token:String) : List<Int>

   suspend fun getToken():String

}