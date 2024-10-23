package com.example.notes_screens_impl.screens.edit_note.domain.usecases

import com.example.notes_screens_impl.screens.edit_note.domain.repository.EditNoteClientApi
import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User

class GetUsersUseCase (

    private val client: EditNoteClientApi,

    ) {

    suspend fun execute( onGet: ( allUsers:MutableList<User>) -> Unit ) {

        client.getUsers (

            onGet

        )
    }
}