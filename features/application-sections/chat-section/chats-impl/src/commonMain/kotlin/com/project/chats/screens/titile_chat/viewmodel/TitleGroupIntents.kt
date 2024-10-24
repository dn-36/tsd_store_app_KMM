package com.project.chats.screens.titile_chat.viewmodel

import com.project.chats.screens.select_contact.domain.User
import kotlinx.coroutines.CoroutineScope

sealed class TitleGroupIntents {

    data class Ready(val list: Set<User>,val scope: CoroutineScope): TitleGroupIntents()

}