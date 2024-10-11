package com.example.notes_screens_impl.screens.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.example.notes_screens_impl.screens.create_notes.screen.CreateNotesScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.notes_network.NotesApi
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.removeHtmlTags
import org.example.project.presentation.crm_feature.edit_note_screen.screen.EditNoteScreen
import org.example.project.presentation.crm_feature.notes_screen.viewmodel.NotesIntents

class NotesViewModel:ViewModel() {
    var notesState by mutableStateOf(NotesState())
    fun processIntent(intents: NotesIntents){
        when(intents){
            is NotesIntents.CreateBookmarks -> {createNotesIntent()}
            is NotesIntents.SetNotes -> {setNotesIntent(intents.coroutineScope)}
            is NotesIntents.EditNote -> {editNote(intents.note)}
        }
    }
    fun createNotesIntent(){

        Navigation.navigator.push(CreateNotesScreen())
    }
    fun editNote(note: NoteResponse){

        Navigation.navigator.push(EditNoteScreen(note))
    }
    fun setNotesIntent(coroutineScope: CoroutineScope){

        val token = ConstData.TOKEN

        NotesApi.token = token

        val notesApi = NotesApi

        coroutineScope.launch(Dispatchers.IO) {

            val notesResponse = notesApi.getNotes()

            // Проходим по каждому элементу списка и очищаем поле text от HTML-тегов
            val cleanedNotesResponse = notesResponse.map { note ->
                note.copy(text = note.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
            }

            notesState = notesState.copy(

                listNotes = cleanedNotesResponse

            )

            println("${notesResponse}")

        }

    }
}