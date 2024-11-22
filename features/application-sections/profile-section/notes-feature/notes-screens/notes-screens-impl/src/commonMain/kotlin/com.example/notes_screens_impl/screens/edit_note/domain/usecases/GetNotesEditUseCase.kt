package com.example.notes_screens_impl.screens.edit_note.domain.usecases

import com.example.notes_screens_impl.screens.edit_note.domain.repository.EditNoteClientApi
import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.NoteResponse

class GetNotesEditUseCase (

    private val client: EditNoteClientApi,

    ) {

    suspend fun execute( note: NoteResponse,
                         onGet: ( note:NoteResponse?) -> Unit,
                         updatedNote: BodyNoteDto
    ) {

        client.getNotes (

            note, onGet, updatedNote

        )
    }
}