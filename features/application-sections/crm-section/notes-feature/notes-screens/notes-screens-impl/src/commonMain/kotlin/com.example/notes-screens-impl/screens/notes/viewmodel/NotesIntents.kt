package org.example.project.presentation.crm_feature.notes_screen.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.example.project.core.model.NoteResponse

sealed class NotesIntents {
    object CreateBookmarks: NotesIntents()
    data class SetNotes (val coroutineScope: CoroutineScope): NotesIntents()
    data class EditNote(val note: NoteResponse): NotesIntents()
}