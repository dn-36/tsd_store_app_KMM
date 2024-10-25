package com.example.notes_screens_impl.screens.edit_note.screen

import com.example.notes_screens_impl.screens.edit_note.component.EditNoteComponent
import com.example.notes_screens_impl.screens.notes.component.NotesComponent
import com.project.core_app.network_base_screen.NetworkScreen
import com.project.network.notes_network.model.NoteResponse
import org.koin.mp.KoinPlatform

class EditNoteScreen(private val noteResponse: NoteResponse): NetworkScreen(

    EditNoteComponent( noteResponse,KoinPlatform.getKoin().get())

)