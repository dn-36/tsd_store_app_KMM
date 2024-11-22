package com.example.notes_screens_impl

import cafe.adriel.voyager.core.screen.Screen
import com.example.`notes-screens-api`.NotesScreensApi
import com.example.notes_screens_impl.screens.notes.screen.NotesScreen
import com.example.notes_screens_impl.screens.create_notes.screen.CreateNotesScreen
import org.koin.mp.KoinPlatform.getKoin


class NotesScreensImpl: NotesScreensApi {
    override fun createNotesScreen(): Screen {
    return  CreateNotesScreen()
    }



    override fun notesScreen(): Screen {
      return NotesScreen()
    }
}