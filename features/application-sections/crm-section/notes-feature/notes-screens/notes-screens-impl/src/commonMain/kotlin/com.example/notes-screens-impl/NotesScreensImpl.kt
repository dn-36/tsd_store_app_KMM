package com.example.`notes-screens-impl`

import cafe.adriel.voyager.core.screen.Screen
import com.example.`notes-screens-api`.NotesScreensApi
import com.example.`notes-screens-impl`.screens.notes.screen.NotesScreen
import com.example.`notes-screens-impl`.screens.create_notes.screen.CreateNotesScreen
import org.example.project.presentation.crm_feature.edit_note_screen.screen.EditNoteScreen
import org.koin.mp.KoinPlatform.getKoin


class NotesScreensImpl: NotesScreensApi {
    override fun createNotesScreen(): Screen {
    return  CreateNotesScreen
    }

    override fun editNoteScreen(): Screen {
        return EditNoteScreen(getKoin().get())
    }

    override fun notesScreen(): Screen {
      return NotesScreen
    }
}