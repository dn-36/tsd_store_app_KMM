package com.example.notes_screens_impl.screens.notes.viewmodel

import com.project.network.notes_network.model.NoteResponse


data class NotesState(

    val listNotes:List<NoteResponse> = emptyList(),

    val listFilteredNotes:List<NoteResponse> = emptyList()

)
