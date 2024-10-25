package org.example.project.presentation.crm_feature.edit_note_screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.DeleteNoteUseCase
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.GetNotesEditUseCase
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.GetUsersUseCase
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.UpdateNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.example.notes_screens_impl.screens.edit_note.screen.EditNoteScreen
import com.example.notes_screens_impl.screens.notes.screen.NotesScreen
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.User

class EditNoteViewModel (

    val updateNoteUseCase: UpdateNoteUseCase,

    val deleteNoteUseCase: DeleteNoteUseCase,

    val getNotesUseCase: GetNotesEditUseCase,

    val getUsersUseCase: GetUsersUseCase,

    ) : NetworkViewModel() {

    var editNoteState by mutableStateOf(EditNoteState())

    fun processIntent(intent: EditNoteIntents) {
        when (intent) {

            is EditNoteIntents.SetScreen -> {

                if (editNoteState.isUsed.value) {

                    editNoteState.isUsed.value = false

                    val idUsers = mutableListOf<Int?>()

                    val updatedUsers = mutableListOf<User>()

                    intent.note.users.forEach { it ->
                        idUsers.add(it.id)
                        updatedUsers.add(it)
                    }

                    var creator = false

                    var heightBox = 0.2f

                    intent.coroutineScope.launch (Dispatchers.IO) {

                        getUsersUseCase.execute ( onGet = {allUsers ->


                            if(intent.note.creater == 1){

                                creator = true

                                heightBox = 0.25f

                            }

                            val status = when (intent.note.status) {
                                1 -> "Активна"
                                0 -> "Скрыта"
                                else -> {
                                    ""
                                }
                            }

                            editNoteState = editNoteState.copy(
                                note = Note(
                                    // id = "note.id",
                                    name = intent.note.name,
                                    text = intent.note.text,
                                    status = intent.note.status,
                                    users = idUsers.toList(),
                                    local_id = "${intent.note.id}"
                                ),
                                noteText = intent.note.text,
                                listAllUsers = allUsers,
                                statusTF = status,
                                titleTF = intent.note.name,
                                status = intent.note.status,
                                usersTF = "",//userText.value,
                                updatedUser = updatedUsers,
                                filteredUsers = editNoteState.listAllUsers,
                                creator = creator,
                                heightBox = heightBox
                            )

                        })

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)


                    }

                    //setScreen(intent.note, intent.coroutineScope)

                }
            }

            is EditNoteIntents.UpdateNoteBack -> {

                val idUsers = mutableListOf<Int?>()

                editNoteState.updatedUser.forEach {
                    idUsers.add(it.id)
                }

                val updatedNote = BodyNoteDto(
                    name = editNoteState.titleTF,
                    text = editNoteState.noteText,
                    status = editNoteState.status,
                    users = idUsers,
                    local_id = "null"
                )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    updateNoteUseCase.execute( intent.note ,updatedNote = updatedNote ,

                        onUpdate = {

                        Navigation.navigator.push(NotesScreen())
                    } )

                }
                //updateNoteBack(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.ApplyNameUpdate -> {

                val idUsers = mutableListOf<Int?>()

                editNoteState.updatedUser.forEach {

                    println("${it.name}")

                    idUsers.add(it.id)

                }

                println("${idUsers}")

                val updatedNote = BodyNoteDto(
                    name = editNoteState.titleTF,
                    text = editNoteState.noteText,
                    status = editNoteState.status,
                    users = idUsers,
                    local_id = "null"
                )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    updateNoteUseCase.execute(intent.note, onUpdate = {}, updatedNote = updatedNote)

                    getNotesUseCase.execute(intent.note, updatedNote = updatedNote, onGet = { note ->

                        Navigation.navigator.push(EditNoteScreen(note!!))

                        editNoteState = editNoteState.copy(
                            openWindowUpdate = false,
                            isUsed = mutableStateOf(true)
                        )
                    })

                }
                //applyNameUpdate(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.Cancel -> {
                cancel()
            }

            is EditNoteIntents.SelectingEditableCategory -> {
                selectingEditableCategory(intent.index)
            }

            is EditNoteIntents.DeleteNote -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteNoteUseCase.execute( intent.note, onDelete = {

                        Navigation.navigator.push(NotesScreen())

                    })

                }
                //deleteNote(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.ApplyStatusUpdate -> {

                val idUsers = mutableListOf<Int?>()

                editNoteState.updatedUser.forEach {
                    println("${it.name}")
                    idUsers.add(it.id)
                }

                println("${idUsers}")

                var status = 1

                when (editNoteState.statusTF) {

                    "Активна" -> {
                        status = 1
                    }

                    "Скрыта" -> {
                        status = 0
                    }

                    else -> {
                        status = 1
                    }

                }

                val updatedNote = BodyNoteDto(
                    name = editNoteState.titleTF,
                    text = editNoteState.noteText,
                    status = status,
                    users = idUsers.toList(),
                    local_id = "null"
                )

                editNoteState = editNoteState.copy(
                    status = status
                )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    updateNoteUseCase.execute(intent.note, onUpdate = {}, updatedNote = updatedNote)

                    if (editNoteState.status == 1) {

                        getNotesUseCase.execute(

                            intent.note,

                            updatedNote = updatedNote,

                            onGet = { note ->

                                Navigation.navigator.push(EditNoteScreen(note!!))

                                editNoteState = editNoteState.copy(

                                    openWindowUpdate = false,

                                    isUsed = mutableStateOf(true)
                                )
                            })
                    }
                    else {

                        Navigation.navigator.push(NotesScreen())

                    }

                }
                //applyStatusUpdate(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.ApplyUsersUpdate -> {

                val idUsers = mutableListOf<Int?>()

                editNoteState.updatedUser.forEach {

                    println("${it.name}")

                    idUsers.add(it.id)

                }

                println("${idUsers}")

                val updatedNote = BodyNoteDto(
                    name = editNoteState.titleTF,
                    text = editNoteState.noteText,
                    status = editNoteState.status,
                    users = idUsers,
                    local_id = "null"
                )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    updateNoteUseCase.execute(intent.note, onUpdate = {}, updatedNote = updatedNote)

                    getNotesUseCase.execute(intent.note, updatedNote = updatedNote, onGet = { note ->

                        Navigation.navigator.push(EditNoteScreen(note!!))

                        editNoteState = editNoteState.copy(
                            openWindowUpdate = false,
                            isUsed = mutableStateOf(true)
                        )
                    })

                }
                //applyUsersUpdate(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.DeleteUserNote -> {
                deleteUsersNote(intent.user)
            }
        }
    }


    fun selectingEditableCategory(index: Int) {

        editNoteState = editNoteState.copy(

            isUsed = mutableStateOf(true),

            openWindowUpdate = true,

            categoryNow = index
        )
    }

    fun cancel() {

        editNoteState = editNoteState.copy(

            openWindowUpdate = false
        )
    }

    fun deleteUsersNote(user: User) {

        val newList = editNoteState.updatedUser.toMutableList()

        newList.remove(user)

        editNoteState = editNoteState.copy(
            updatedUser = newList
        )
    }

}