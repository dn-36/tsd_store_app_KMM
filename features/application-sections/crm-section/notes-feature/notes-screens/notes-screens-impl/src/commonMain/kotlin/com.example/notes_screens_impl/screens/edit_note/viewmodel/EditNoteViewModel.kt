package org.example.project.presentation.crm_feature.edit_note_screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.presentation.crm_feature.edit_note_screen.screen.EditNoteScreen
import com.example.notes_screens_impl.screens.notes.screen.NotesScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.notes_network.NotesApi
import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.Note
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User
import com.project.network.notes_network.model.removeHtmlTags

class EditNoteViewModel : ViewModel() {
    var editNoteState by mutableStateOf(EditNoteState())


    fun processIntent(intent: EditNoteIntents) {
        when (intent) {
            is EditNoteIntents.SetScreen -> {
                setScreen(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.UpdateNoteBack -> {
                updateNoteBack(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.ApplyNameUpdate -> {
                applyNameUpdate(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.Cancel -> {
                cancel()
            }

            is EditNoteIntents.SelectingEditableCategory -> {
                selectingEditableCategory(intent.index)
            }

            is EditNoteIntents.DeleteNote -> {
                deleteNote(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.ApplyStatusUpdate -> {
                applyStatusUpdate(intent.note, intent.coroutineScope)
            }

            is EditNoteIntents.ApplyUsersUpdate -> {
                applyUsersUpdate(intent.note, intent.coroutineScope)
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

    fun applyStatusUpdate(note: NoteResponse, coroutineScope: CoroutineScope) {

        val token = ConstData.TOKEN

        val notesApi = NotesApi
        NotesApi.token = token

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

        println("2")
        println("2")
        println("2")
        println("${updatedNote}")
        println("2")
        println("2")

        coroutineScope.launch(Dispatchers.IO) {

            notesApi.updateNote(noteId = "${note.ui}", updatedNote = updatedNote)

            if (editNoteState.status == 1) {

                val notesResponse = notesApi.getNotes()

                // Проходим по каждому элементу списка и очищаем поле text от HTML-тегов
                val cleanedNotesResponse = notesResponse.map { note ->
                    note.copy(text = note.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
                }


                val notes = cleanedNotesResponse.find { item ->
                    item.ui == note.ui
                }

                Navigation.navigator.push(EditNoteScreen(notes!!))

                editNoteState = editNoteState.copy(
                    openWindowUpdate = false,
                    isUsed = mutableStateOf(true)
                )
            } else {

                Navigation.navigator.push(NotesScreen)

            }
        }
    }

    fun applyNameUpdate(note: NoteResponse, coroutineScope: CoroutineScope) {

        val token = ConstData.TOKEN

        val notesApi = NotesApi
        NotesApi.token = token

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
            users = idUsers.toList(),
            local_id = "null"
        )

        println("2")
        println("2")
        println("2")
        println("${idUsers}")
        println("2")
        println("2")

        coroutineScope.launch(Dispatchers.IO) {

            notesApi.updateNote(noteId = "${note.ui}", updatedNote = updatedNote)

            val notesResponse = notesApi.getNotes()

            // Проходим по каждому элементу списка и очищаем поле text от HTML-тегов
            val cleanedNotesResponse = notesResponse.map { note ->
                note.copy(text = note.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
            }


            val notes = cleanedNotesResponse.find { item ->
                item.ui == note.ui
            }

            Navigation.navigator.push(EditNoteScreen(notes!!))

            editNoteState = editNoteState.copy(
                openWindowUpdate = false,
                isUsed = mutableStateOf(true)
            )
        }
    }

    fun applyUsersUpdate(note: NoteResponse, coroutineScope: CoroutineScope) {

        val token = ConstData.TOKEN

        val notesApi = NotesApi

        NotesApi.token = token

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

        println("2")
        println("2")
        println("2")
        println("${idUsers}")
        println("2")
        println("2")

        coroutineScope.launch(Dispatchers.IO) {

            notesApi.updateNote(noteId = "${note.ui}", updatedNote = updatedNote)

            val notesResponse = notesApi.getNotes()

            // Проходим по каждому элементу списка и очищаем поле text от HTML-тегов
            val cleanedNotesResponse = notesResponse.map { note ->
                note.copy(text = note.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
            }


            val notes = cleanedNotesResponse.find { item ->
                item.ui == note.ui
            }

            Navigation.navigator.push(EditNoteScreen(notes!!))

            editNoteState = editNoteState.copy(
                openWindowUpdate = false,
                isUsed = mutableStateOf(true)
            )

        }
    }

    fun deleteUsersNote(user: User) {

        val newList = editNoteState.updatedUser.toMutableList()

        newList.remove(user)

        editNoteState = editNoteState.copy(
            updatedUser = newList
        )
    }

    fun setScreen(note: NoteResponse, coroutineScope: CoroutineScope) {

        if (editNoteState.isUsed.value) {

            editNoteState.isUsed.value = false

            val idUsers = mutableListOf<Int?>()

            val updatedUsers = mutableListOf<User>()

            val token = ConstData.TOKEN

            val notesApi = NotesApi

            // val usersApi = UsersApi

            NotesApi.token = token

            note.users.forEach { it ->
                idUsers.add(it.id)
                updatedUsers.add(it)
            }

            val allUsers = mutableListOf<User>()

            var creator = false

            var heightBox = 0.2f

            coroutineScope.launch(Dispatchers.IO) {

                notesApi.getUsers().forEach {
                    allUsers.add(it)
                }

                if(note.creater == 1){

                creator = true

                heightBox = 0.25f

                }

                var userText = mutableStateOf<String?>("")

                val status = when (note.status) {
                    1 -> "Активна"
                    0 -> "Скрыта"
                    else -> {
                        ""
                    }
                }

                note.users.forEach { it ->

                    println("${it.name}")

                    userText.value = "${userText.value + it.name},"
                }

                println("${userText.value}")

                editNoteState = editNoteState.copy(
                    note = Note(
                        // id = "note.id",
                        name = note.name,
                        text = note.text,
                        status = note.status,
                        users = idUsers.toList(),
                        local_id = "${note.id}"
                    ),
                    noteText = note.text,
                    listAllUsers = allUsers,
                    statusTF = status,
                    titleTF = note.name,
                    status = note.status,
                    usersTF = "",//userText.value,
                    updatedUser = updatedUsers,
                    filteredUsers = editNoteState.listAllUsers,
                    creator = creator,
                    heightBox = heightBox
                )

                println("5")
                println("5")
                println("5")
                println("${editNoteState.statusTF}")
                println("5")
                println("5")
                println("5")
            }

        }
    }

    fun updateNoteBack(note: NoteResponse, coroutineScope: CoroutineScope) {


        val idUsers = mutableListOf<Int?>()

        editNoteState.updatedUser.forEach {
            idUsers.add(it.id)
        }


        val token = ConstData.TOKEN

        val notesApi = NotesApi

        NotesApi.token = token

        val updatedNote = BodyNoteDto(
            name = editNoteState.titleTF,
            text = editNoteState.noteText,
            status = editNoteState.status,
            users = idUsers,
            local_id = "null"
        )

        println("2")
        println("2")
        println("2")
        println("${updatedNote}")
        println("2")
        println("2")

        coroutineScope.launch(Dispatchers.IO) {

            notesApi.updateNote(noteId = "${note.ui}", updatedNote = updatedNote)

            Navigation.navigator.push(NotesScreen)

        }
    }

    fun deleteNote(note: NoteResponse, coroutineScope: CoroutineScope) {

        val token = ConstData.TOKEN

        val notesApi = NotesApi
        NotesApi.token = token

        coroutineScope.launch(Dispatchers.IO) {

            notesApi.deleteNote(noteId = "${note.ui}")

            Navigation.navigator.push(NotesScreen)

        }

    }

}