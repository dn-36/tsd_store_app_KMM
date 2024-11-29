package com.example.notes_screens_impl.screens.edit_note.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User


data class EditNoteState(
    val note: Note = Note(name = null,text = null,status = null,users = listOf(),local_id = "null"),
    val noteResponse: NoteResponse = NoteResponse(
        name = null,text = null,status = null,users = listOf(),
        view = null, active = null,fon = null, creater = null, sort = null,ui = null,
        project = null,id =null,user = null, updatedAt = null, openLink = null,chat = null,
        countNewMessage = null
    ),
    val isUsed:MutableState<Boolean> = mutableStateOf(true),
    val noteText: String? = "",
    val titleTF: String? = "",
    val usersTF: String? = "",
    val expandedSettings:Boolean = false,
    val settingsName:Int = 0,
    val expandedList:Boolean = false,
    val statusTF:String? = "",
    val openWindowUpdate:Boolean = false,
    val creator:Boolean = false,
    val categoryNow:Int = 5,
    val status:Int? = 1,
    val filteredUsers: List<User> = emptyList(),
    val listAllUsers:MutableList<User> = mutableListOf(),
    val updatedUser:MutableList<User> = mutableListOf()
)
