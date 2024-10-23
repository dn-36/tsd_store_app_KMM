package com.project.chats.screens.chats.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ChatsIntents {

    data class DialogueSelection(
        val scope:CoroutineScope,
        val ui:String,
        val urlIcon:String?,
        val titleChat:String,
        val countNewMessage:Int
        )
        : ChatsIntents()

    object AddChat : ChatsIntents()
    data class SetScreen(val scope:CoroutineScope) : ChatsIntents()
}