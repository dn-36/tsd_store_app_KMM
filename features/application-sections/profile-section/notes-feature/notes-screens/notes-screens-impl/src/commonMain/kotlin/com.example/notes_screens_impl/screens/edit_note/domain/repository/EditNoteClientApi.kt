package com.example.notes_screens_impl.screens.edit_note.domain.repository

import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User

interface EditNoteClientApi {

    suspend fun deleteNote ( note: NoteResponse, onDelete: () -> Unit )

    suspend fun getUsers ( onGet: ( allUsers:MutableList<User>) -> Unit )

    suspend fun updateNote ( note: NoteResponse, onUpdate: () -> Unit, updatedNote: BodyNoteDto )

    suspend fun getNotes ( note: NoteResponse, onGet: ( note:NoteResponse?) -> Unit, updatedNote: BodyNoteDto )

}