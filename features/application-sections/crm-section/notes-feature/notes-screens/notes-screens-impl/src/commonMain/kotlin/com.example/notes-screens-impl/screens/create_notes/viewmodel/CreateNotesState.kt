package com.example.`notes-screens-impl`.screens.create_notes.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.project.network.notes_network.model.User

data class CreateNotesState(
    val name:String = "",
    val status:String = "",
    val users:String = "",
    val description:String = "",
    val listAllUsers:List<User> = listOf(),
    val expandedUsers:Boolean = false,
    val expandedStatus:Boolean = false,
    val filteredUsers: List<User> = emptyList(),
    val usersNoteCreated:MutableList<User> = mutableListOf<User>(),
    val isUsed: MutableState<Boolean> = mutableStateOf(true)
)
