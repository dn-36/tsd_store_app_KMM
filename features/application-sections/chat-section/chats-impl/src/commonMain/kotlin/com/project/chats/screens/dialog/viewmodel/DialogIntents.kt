package com.project.chats.screens.dialog.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class DialogIntents {
    object Back : DialogIntents()
    object HistoryFiles : DialogIntents()
    data class SetScreen(
        val uiChats:String,
        val scope:CoroutineScope
    ) : DialogIntents()
}