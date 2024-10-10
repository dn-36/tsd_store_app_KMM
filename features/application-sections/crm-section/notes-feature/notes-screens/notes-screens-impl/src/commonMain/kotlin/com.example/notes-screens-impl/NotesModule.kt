package com.example.`notes-screens-impl`

import com.example.`notes-screens-api`.NotesScreensApi
import com.example.`notes-screens-impl`.screens.notes.screen.NotesScreen
import org.koin.dsl.module

val notesModule = module {
    NotesScreensImpl() as NotesScreensApi
}
