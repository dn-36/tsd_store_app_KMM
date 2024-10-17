package com.example.notes_screens_impl.screens.edit_note.datasource

import com.example.notes_screens_impl.screens.edit_note.domain.repository.EditNoteClientApi
import com.project.network.notes_network.NotesClient
import com.project.network.notes_network.model.BodyNoteDto
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.User
import com.project.network.notes_network.model.removeHtmlTags

class EditNoteClientImpl(

    private val notesClient: NotesClient

) : EditNoteClientApi {

    override suspend fun deleteNote(note: NoteResponse, onDelete: () -> Unit) {

        notesClient.deleteNote(noteId = "${note.ui}")

        onDelete()

    }

    override suspend fun updateNote(
        note: NoteResponse,
        onUpdate: () -> Unit,
        updatedNote: BodyNoteDto
    ) {

        notesClient.updateNote(noteId = "${note.ui}", updatedNote = updatedNote)

        onUpdate()

    }

    override suspend fun getNotes(
        note: NoteResponse,
        onGet: (note: NoteResponse?) -> Unit,
        updatedNote: BodyNoteDto
    ) {

        notesClient.updateNote(noteId = "${note.ui}", updatedNote = updatedNote)

        val notesResponse = notesClient.getNotes()

        // Проходим по каждому элементу списка и очищаем поле text от HTML-тегов
        val cleanedNotesResponse = notesResponse.map { note ->
            note.copy(text = note.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
        }


        val notes = cleanedNotesResponse.find { item ->
            item.ui == note.ui
        }

        onGet(notes)

    }

    override suspend fun getUsers(
        onGet: (allUsers: MutableList<User>) -> Unit
    ) {

        onGet(notesClient.getUsers().toMutableList())

    }

}