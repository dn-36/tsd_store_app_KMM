package com.project.chats.screens.titile_chat.screen

import com.project.chats.screens.select_contact.domain.User
import com.project.chats.screens.titile_chat.components.TitleChatComponent
import com.project.core_app.network_base_screen.NetworkScreen

class TitleChatScreen(listUser:Set<User>):NetworkScreen(
    TitleChatComponent(listUser)
) {
}