package com.example.notes_screens_impl.screens.notes.datasource

import com.example.notes_screens_impl.screens.notes.domain.repository.NotesClientApi
import com.project.network.notes_network.NotesClient
import com.project.network.notes_network.model.NoteResponse
import com.project.network.notes_network.model.removeHtmlTags
import com.project.network.organizations_network.OrganizationsClient

class NotesClientImpl (

    private val notesClient: NotesClient

) : NotesClientApi {

    override suspend fun getNotes(onGet: (listNotes: List<NoteResponse>) -> Unit) {

       val allNotes = notesClient.getNotes()

        // Проходим по каждому элементу списка и очищаем поле text от HTML-тегов
        val cleanedNotesResponse = allNotes.map { note ->
            note.copy(text = note.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
        }

        onGet (cleanedNotesResponse)

    }

}