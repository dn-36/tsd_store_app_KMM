package com.example.notes_screens_impl.screens.edit_note.domain.usecases

import com.example.notes_screens_impl.screens.edit_note.domain.repository.EditNoteClientApi
import com.project.network.notes_network.model.NoteResponse

class DeleteNoteUseCase (

    private val client: EditNoteClientApi,

    ) {

    suspend fun execute( note: NoteResponse,
                         onDelete: () -> Unit ) {

        client.deleteNote (

           note, onDelete

        )
    }
}