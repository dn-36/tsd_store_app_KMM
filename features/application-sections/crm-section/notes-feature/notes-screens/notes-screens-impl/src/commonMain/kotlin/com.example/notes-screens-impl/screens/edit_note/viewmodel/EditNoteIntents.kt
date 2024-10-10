package org.example.project.presentation.crm_feature.edit_note_screen.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.example.project.core.model.NoteResponse
import org.example.project.core.model.User

sealed class EditNoteIntents {
    data class SetScreen(val note: NoteResponse,val coroutineScope: CoroutineScope):EditNoteIntents()
    data class UpdateNoteBack(val note: NoteResponse, val coroutineScope: CoroutineScope):EditNoteIntents()

   // object Back:EditNoteIntents()
    object Cancel:EditNoteIntents()
    data class ApplyNameUpdate(val note: NoteResponse, val coroutineScope: CoroutineScope):EditNoteIntents()
    data class ApplyStatusUpdate(val note: NoteResponse, val coroutineScope: CoroutineScope):EditNoteIntents()
    data class ApplyUsersUpdate(val note: NoteResponse, val coroutineScope: CoroutineScope):EditNoteIntents()
    data class DeleteUserNote(val user: User):EditNoteIntents()
    data class DeleteNote(val note: NoteResponse,val coroutineScope: CoroutineScope):EditNoteIntents()
    data class SelectingEditableCategory(val index:Int):EditNoteIntents()
}