package com.example.notes_screens_impl.screens.notes.domain.usecases

import androidx.compose.ui.graphics.Color
import com.example.notes_screens_impl.screens.notes.domain.repository.NotesClientApi
import com.project.network.notes_network.model.NoteResponse
import com.project.network.organizations_network.model.Response

class GetNotesUseCase (

    private val client: NotesClientApi,

    ) {

    suspend fun execute( onGet: (listNotes: List<NoteResponse> ) -> Unit ) {

        client.getNotes (

            onGet

        )
    }
}