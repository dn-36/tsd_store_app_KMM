package org.example.project.presentation.project_control.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ProjectControlIntents {
    object Back:ProjectControlIntents()
    data class SetScreen( val coroutineScope: CoroutineScope ):ProjectControlIntents()
    data class OpenCreateDataEntryComponent( val coroutineScope: CoroutineScope ):ProjectControlIntents()
    data class OpenDescription( val index: Int ):ProjectControlIntents()
}