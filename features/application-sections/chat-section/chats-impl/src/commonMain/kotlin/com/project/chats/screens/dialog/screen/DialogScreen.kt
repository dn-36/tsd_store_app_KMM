package com.project.chats.screens.dialog.screen


import com.project.chats.screens.dialog.components.DialogComponentScreen
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin


class DialogScreen(
    private val uiChats:String,
    private val titleChat:String,
    private val urlIcon:String?,
    private val countNewMessage:Int
) : NetworkScreen(
      DialogComponentScreen(
        uiChats,
        titleChat,
        urlIcon,
      //  countNewMessage,
        getKoin().get()
    )
)




