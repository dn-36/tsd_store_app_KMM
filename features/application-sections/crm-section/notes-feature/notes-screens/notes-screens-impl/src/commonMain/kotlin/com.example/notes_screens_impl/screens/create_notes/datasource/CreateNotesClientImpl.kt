package com.example.notes_screens_impl.screens.create_notes.datasource

import com.example.notes_screens_impl.screens.create_notes.domain.repository.CreateNotesClientApi
import com.project.network.notes_network.NotesClient
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.User

class CreateNotesClientImpl (

    private val notesClient: NotesClient

) : CreateNotesClientApi {

    override suspend fun getUsersCreate( onGet: (listUsers: List<User> ) -> Unit) {

       onGet(notesClient.getUsers())

    }

    override suspend fun createNote(note: Note) {

        notesClient.createNote(note)

    }

}