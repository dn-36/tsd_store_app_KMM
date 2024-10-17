package com.project.chats.screens.chats.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ChatsIntents {

    data class DialogueSelection(
        val scope:CoroutineScope,
        val ui:String,
        val titleChat:String)
        : ChatsIntents()

    object AddChat : ChatsIntents()
    data class SetScreen(val scope:CoroutineScope) : ChatsIntents()
}