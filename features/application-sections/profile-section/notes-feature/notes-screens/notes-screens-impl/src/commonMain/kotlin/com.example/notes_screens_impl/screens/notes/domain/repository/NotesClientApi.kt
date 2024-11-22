package com.example.notes_screens_impl.screens.notes.domain.repository

import com.project.network.notes_network.model.NoteResponse

interface NotesClientApi {

    suspend fun getNotes ( onGet: (listNotes: List<NoteResponse> ) -> Unit )

}