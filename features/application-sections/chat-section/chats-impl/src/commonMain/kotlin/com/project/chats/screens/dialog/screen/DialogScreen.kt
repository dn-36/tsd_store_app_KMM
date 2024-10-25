package com.project.chats.screens.dialog.screen


import com.project.chats.screens.dialog.components.DialogComponent
import com.project.core_app.network_base_screen.NetworkScreen


class DialogScreen(
    private val uiChats:String,
    private val titleChat:String,
    private val urlIcon:String?,
    private val countNewMessage:Int
) : NetworkScreen(
    DialogComponent(
        uiChats,
        titleChat,
        urlIcon,
        countNewMessage
    )
)




