package com.project.chats.screens.select_contact.viewmodel

import androidx.compose.ui.graphics.Color
import com.project.chats.screens.select_contact.domain.User

data class SelectContactsState(
    val listContacts: List<User> = emptyList(),
    val text: String = "",
    val colorButtonAll: Color = Color(0xFF60BCE6),
    val colorButtonOrganization: Color = Color.LightGray,
    val selectedListContacts: Set<User> = setOf()
)
