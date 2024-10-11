package org.example.project.presentation.crm_feature.create_notes_screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notes_screens_impl.screens.create_notes.viewmodel.CreateNotesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.example.notes_screens_impl.screens.notes.screen.NotesScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.notes_network.NotesApi
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.User

class CreateNotesViewModel():ViewModel() {

    var createNotesState by mutableStateOf(CreateNotesState())

    fun processIntent(intents: CreateNotesIntents){
        when(intents){
            is CreateNotesIntents.Next -> {next(intents.coroutineScope)
            }
            is CreateNotesIntents.Cancel -> {cancel()}

            is CreateNotesIntents.Back -> {back()}

            is CreateNotesIntents.DeleteUserNote -> {deleteUsersNote(intents.user)}

            is CreateNotesIntents.GetAllUsersList -> {getAllUsersList(intents.coroutineScope)}
        }
    }
    fun next(coroutineScope: CoroutineScope){

        val token = ConstData.TOKEN

        val notesApi = NotesApi

        NotesApi.token = token

        val statusInt = when(createNotesState.status){

            "Активна" -> { 1 }

            "Скрыта" -> { 0 }

            else -> { 1 }
        }

        val idUsers = mutableListOf<Int?>()

        createNotesState.usersNoteCreated.forEach { it ->
            idUsers.add(it.id)
        }

        coroutineScope.launch(Dispatchers.IO) {

            val note = Note(
                name = createNotesState.name,
                text = createNotesState.description,
                status = statusInt,
                users = idUsers,
                local_id = "9090")

            notesApi.createNote(note)

            Navigation.navigator.push(NotesScreen())

            createNotesState = createNotesState.copy(
                name = "",
                status = "",
                users = "",
                description = "",
                listAllUsers = listOf(),
                expandedUsers = false,
                expandedStatus = false,
                usersNoteCreated = mutableListOf(),
                isUsed = mutableStateOf(true)
            )
        }



}
    fun cancel(){
        Navigation.navigator.push(NotesScreen())

        createNotesState = createNotesState.copy(
            name = "",
            status = "",
            users = "",
            description = "",
            listAllUsers = listOf(),
            expandedUsers = false,
            expandedStatus = false,
            usersNoteCreated = mutableListOf(),
            isUsed = mutableStateOf(true)
        )
    }
    fun back(){

        Navigation.navigator.push(NotesScreen())

        createNotesState = createNotesState.copy(
            name = "",
            status = "",
            users = "",
            description = "",
            listAllUsers = listOf(),
            expandedUsers = false,
            expandedStatus = false,
            usersNoteCreated = mutableListOf(),
            isUsed = mutableStateOf(true)
        )
    }

    fun deleteUsersNote(user: User){

        val newList = createNotesState.usersNoteCreated.toMutableList()

        newList.remove(user)

        createNotesState = createNotesState.copy(
            usersNoteCreated = newList
        )
    }

    fun getAllUsersList(coroutineScope: CoroutineScope){

        if(createNotesState.isUsed.value) {

            createNotesState.isUsed.value = false

            val token = ConstData.TOKEN

            val notesApi = NotesApi

           // val usersApi = UsersApi

            NotesApi.token = token

            coroutineScope.launch(Dispatchers.IO) {

                createNotesState = createNotesState.copy(
                    listAllUsers = notesApi.getUsers(),
                    filteredUsers = notesApi.getUsers()
                )
            }

            println("9:${createNotesState.listAllUsers}")

        }
    }
}