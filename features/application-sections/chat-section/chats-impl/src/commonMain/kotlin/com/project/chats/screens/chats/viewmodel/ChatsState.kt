package com.project.chats.screens.chats.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.project.chats.screens.chats.domain.models.ChatsModel

data class ChatsState(
    val isShowDeleteDialog:Boolean = false,
    val listchats:List<ChatsModel> = listOf(),

)
