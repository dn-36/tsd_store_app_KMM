package com.example.notes_screens_impl.screens.create_notes.screen


import com.example.notes_screens_impl.screens.create_notes.component.CreateNoteComponent
import com.example.notes_screens_impl.screens.edit_note.component.EditNoteComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin


class CreateNotesScreen : NetworkScreen(

    CreateNoteComponent( getKoin().get())

)