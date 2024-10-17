package com.example.notes_screens_impl.screens.create_notes.domain.repository

import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.User

interface CreateNotesClientApi {

    suspend fun getUsersCreate (onGet: (listUsers:List<User>) -> Unit )

    suspend fun createNote ( note: Note)

}