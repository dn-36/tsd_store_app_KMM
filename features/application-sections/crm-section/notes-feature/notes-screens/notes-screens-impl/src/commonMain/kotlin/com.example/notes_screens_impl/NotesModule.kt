package com.example.notes_screens_impl

import com.example.`notes-screens-api`.NotesScreensApi
import org.koin.dsl.module

val notesModule = module {
    factory {
        NotesScreensImpl() as NotesScreensApi
    }
}
