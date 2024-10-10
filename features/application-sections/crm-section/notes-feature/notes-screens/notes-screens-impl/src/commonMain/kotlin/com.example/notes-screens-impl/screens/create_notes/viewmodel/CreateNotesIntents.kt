package org.example.project.presentation.crm_feature.create_notes_screen.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.example.project.core.model.User

sealed class CreateNotesIntents {
    object Cancel: CreateNotesIntents()
    object Back: CreateNotesIntents()

    data class GetAllUsersList(val coroutineScope: CoroutineScope):CreateNotesIntents()
    data class Next(val coroutineScope: CoroutineScope):CreateNotesIntents()
    data class DeleteUserNote(val user: User):CreateNotesIntents()
}