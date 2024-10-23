package com.example.notes_screens_impl.screens.create_notes.domain.usecases

import com.example.notes_screens_impl.screens.create_notes.domain.repository.CreateNotesClientApi
import com.project.network.notes_network.model.User

class GetUsersCreateUseCase (

    private val client: CreateNotesClientApi,

    ) {

    suspend fun execute( onGet: ( allUsers:List<User>) -> Unit ) {

        client.getUsersCreate (

            onGet

        )
    }
}