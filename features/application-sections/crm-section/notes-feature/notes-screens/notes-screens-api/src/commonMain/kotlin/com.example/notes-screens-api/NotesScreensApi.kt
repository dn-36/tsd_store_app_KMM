package com.example.`notes-screens-api`

import cafe.adriel.voyager.core.screen.Screen


interface NotesScreensApi {

    fun createNotesScreen() : Screen
    fun editNoteScreen() : Screen
    fun notesScreen() : Screen

}