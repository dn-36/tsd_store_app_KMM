package com.example.notes_screens_impl.screens.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.example.notes_screens_impl.screens.create_notes.screen.CreateNotesScreen
import com.example.notes_screens_impl.screens.notes.domain.usecases.GetNotesUseCase
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import com.project.network.notes_network.model.NoteResponse
import com.example.notes_screens_impl.screens.edit_note.screen.EditNoteScreen
import com.project.chats.ProfileScreensApi
import org.example.project.presentation.crm_feature.notes_screen.viewmodel.NotesIntents
import org.koin.mp.KoinPlatform

class NotesViewModel (

    val getNotesUseCase: GetNotesUseCase

) : NetworkViewModel() {

    var state by mutableStateOf(NotesState())

    fun processIntent(intents: NotesIntents){

        when(intents){

            is NotesIntents.CreateBookmarks -> createNotesIntent()

            is NotesIntents.Back -> back()

            is NotesIntents.SetNotes -> {

                intents.coroutineScope.launch (Dispatchers.IO) {

                    getNotesUseCase.execute ( onGet = { listAllNotes ->

                        state = state.copy(

                            listNotes = listAllNotes,

                            listFilteredNotes = listAllNotes

                        )

                    })

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is NotesIntents.EditNote -> editNote(intents.note)

            is NotesIntents.InputTextSearchComponent -> inputTextSearchComponent(intents.text)
        }
    }

    fun createNotesIntent(){

        Navigation.navigator.push(CreateNotesScreen())
    }

    fun editNote(note: NoteResponse){

        Navigation.navigator.push(EditNoteScreen(note))
    }

    fun back (){

        val profileScreen: ProfileScreensApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( profileScreen.profile() )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listNotes.filter {

            val companyName = it.name.orEmpty()

            companyName.contains(text, ignoreCase = true)

        }

        state = state.copy(

            listFilteredNotes = newList

        )

        println("Text ${text}")

        println("NewList ${newList}")

    }

}