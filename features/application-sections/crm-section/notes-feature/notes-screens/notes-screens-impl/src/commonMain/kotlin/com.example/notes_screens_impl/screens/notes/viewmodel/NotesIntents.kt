package org.example.project.presentation.crm_feature.notes_screen.viewmodel

import com.project.network.notes_network.model.NoteResponse
import kotlinx.coroutines.CoroutineScope

sealed class NotesIntents {
    object CreateBookmarks: NotesIntents()
    object Back: NotesIntents()
    data class SetNotes (val coroutineScope: CoroutineScope): NotesIntents()
    data class EditNote(val note: NoteResponse): NotesIntents()
}