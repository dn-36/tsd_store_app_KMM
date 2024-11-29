package org.example.project.presentation.crm_feature.create_notes_screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.notes_screens_impl.screens.create_notes.domain.usecases.CreateNoteUseCase
import com.example.notes_screens_impl.screens.create_notes.domain.usecases.GetUsersCreateUseCase
import com.example.notes_screens_impl.screens.create_notes.viewmodel.CreateNotesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.example.notes_screens_impl.screens.notes.screen.NotesScreen
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.User

class CreateNotesViewModel (

    val createNoteUseCase: CreateNoteUseCase,

    val getUsersCreateUseCase: GetUsersCreateUseCase,

) : NetworkViewModel() {

    var state by mutableStateOf(CreateNotesState())

    fun processIntent(intents: CreateNotesIntents){

        when(intents){

            is CreateNotesIntents.Next -> {

                val statusInt = when(state.status){

                    "Активна" -> { 1 }

                    "Скрыта" -> { 0 }

                    else -> { 1 }
                }

                val idUsers = mutableListOf<Int?>()

                state.usersNoteCreated.forEach { it ->
                    idUsers.add(it.id)
                }

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intents.coroutineScope.launch(Dispatchers.IO) {

                    val note = Note(
                        name = state.name,
                        text = state.description,
                        status = statusInt,
                        users = idUsers,
                        local_id = "9090")

                    createNoteUseCase.execute( note )

                    Navigation.navigator.push(NotesScreen())

                    state = state.copy(
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

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
                }
            //next(intents.coroutineScope)
            }
            is CreateNotesIntents.Cancel -> {cancel()}

            is CreateNotesIntents.Back -> {back()}

            is CreateNotesIntents.DeleteUserNote -> { deleteUsersNote(intents.user) }

            is CreateNotesIntents.GetAllUsersList -> {

                if(state.isUsed.value) {

                    state.isUsed.value = false

                    setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                    intents.coroutineScope.launch (Dispatchers.IO) {

                        getUsersCreateUseCase.execute (onGet = { allUsers ->

                            state = state.copy(
                                listAllUsers = allUsers,
                                filteredUsers = allUsers
                            )
                        })

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }

                    println("9:${state.listAllUsers}")

                }
                //getAllUsersList(intents.coroutineScope)
            }
        }
    }

    fun cancel(){
        Navigation.navigator.push(NotesScreen())

        state = state.copy(
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

        state = state.copy(
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

        val newList = state.usersNoteCreated.toMutableList()

        newList.remove(user)

        state = state.copy(
            usersNoteCreated = newList
        )
    }

}