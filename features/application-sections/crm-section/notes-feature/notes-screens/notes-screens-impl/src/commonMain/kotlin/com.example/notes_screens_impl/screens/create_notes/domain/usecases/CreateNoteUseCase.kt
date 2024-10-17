package com.example.notes_screens_impl.screens.create_notes.domain.usecases

import com.example.notes_screens_impl.screens.create_notes.domain.repository.CreateNotesClientApi
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.User

class CreateNoteUseCase (

    private val client: CreateNotesClientApi,

    ) {

    suspend fun execute( note: Note ) {

        client.createNote (

            note

        )
    }
}