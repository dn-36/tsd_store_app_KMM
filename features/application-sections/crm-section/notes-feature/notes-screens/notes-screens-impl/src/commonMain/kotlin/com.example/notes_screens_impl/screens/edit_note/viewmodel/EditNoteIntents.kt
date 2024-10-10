package org.example.project.presentation.crm_feature.edit_note_screen.viewmodel

import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User
import kotlinx.coroutines.CoroutineScope


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