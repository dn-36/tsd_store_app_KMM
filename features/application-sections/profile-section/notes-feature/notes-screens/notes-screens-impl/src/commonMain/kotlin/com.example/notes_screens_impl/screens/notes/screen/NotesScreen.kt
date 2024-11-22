package com.example.notes_screens_impl.screens.notes.screen

import com.example.notes_screens_impl.screens.notes.component.NotesComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin

class NotesScreen:NetworkScreen(

    NotesComponent(getKoin().get())
)