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
import com.example.notes_screens_impl.screens.notes.domain.usecases.GetNotesUseCase
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.notes_network.NotesClient
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.removeHtmlTags
import org.example.project.presentation.crm_feature.edit_note_screen.screen.EditNoteScreen
import org.example.project.presentation.crm_feature.notes_screen.viewmodel.NotesIntents

class NotesViewModel (

    val getNotesUseCase: GetNotesUseCase

) :ViewModel() {

    var notesState by mutableStateOf(NotesState())

    fun processIntent(intents: NotesIntents){

        when(intents){

            is NotesIntents.CreateBookmarks -> {createNotesIntent()}

            is NotesIntents.SetNotes -> {

                intents.coroutineScope.launch (Dispatchers.IO) {

                    getNotesUseCase.execute ( onGet = { listAllNotes ->

                        notesState = notesState.copy(

                            listNotes = listAllNotes

                        )

                    })

                }

            //setNotesIntent(intents.coroutineScope)
            }

            is NotesIntents.EditNote -> {editNote(intents.note)}
        }
    }

    fun createNotesIntent(){

        Navigation.navigator.push(CreateNotesScreen())
    }

    fun editNote(note: NoteResponse){

        Navigation.navigator.push(EditNoteScreen(note))
    }
}