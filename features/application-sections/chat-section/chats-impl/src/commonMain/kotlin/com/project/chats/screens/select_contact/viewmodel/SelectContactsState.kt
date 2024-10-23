package org.example.project.presentation.nika_screens_chats.add_chat_feature.select_contacts_screen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import model.Contact

data class SelectContactsState(

    val filteredContacts: List<Contact> = emptyList(),

    val listContactsPhone: List<Contact> = emptyList(),

    val listContactsOrhanization: List<Contact> = emptyList(),

    val isUsed: MutableState<Boolean> = mutableStateOf(true),

    val text: String = "",

    val colorButtonAll: Color = Color(0xFF60BCE6),

    val colorButtonOrganization: Color = Color.LightGray,

    val listSelectedContacts: MutableList<Contact> = mutableListOf()

)
