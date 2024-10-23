package com.example.notes_screens_impl

import com.example.`notes-screens-api`.NotesScreensApi
import com.example.notes_screens_impl.screens.create_notes.datasource.CreateNotesClientImpl
import com.example.notes_screens_impl.screens.create_notes.domain.repository.CreateNotesClientApi
import com.example.notes_screens_impl.screens.create_notes.domain.usecases.CreateNoteUseCase
import com.example.notes_screens_impl.screens.create_notes.domain.usecases.GetUsersCreateUseCase
import com.example.notes_screens_impl.screens.edit_note.datasource.EditNoteClientImpl
import com.example.notes_screens_impl.screens.edit_note.domain.repository.EditNoteClientApi
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.DeleteNoteUseCase
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.GetNotesEditUseCase
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.GetUsersUseCase
import com.example.notes_screens_impl.screens.edit_note.domain.usecases.UpdateNoteUseCase
import com.example.notes_screens_impl.screens.notes.datasource.NotesClientImpl
import com.example.notes_screens_impl.screens.notes.domain.repository.NotesClientApi
import com.example.notes_screens_impl.screens.notes.domain.usecases.GetNotesUseCase
import com.example.notes_screens_impl.screens.notes.viewmodel.NotesViewModel
import com.project.network.notes_network.NotesClient
import org.example.project.presentation.crm_feature.create_notes_screen.viewmodel.CreateNotesViewModel
import org.example.project.presentation.crm_feature.edit_note_screen.viewmodel.EditNoteViewModel
import org.koin.dsl.module

val notesModule = module {

    factory { NotesScreensImpl() as NotesScreensApi }

    factory { NotesClient() }

    factory { NotesClientImpl ( get() ) as NotesClientApi }

    factory { CreateNotesClientImpl ( get() ) as CreateNotesClientApi }

    factory { GetNotesUseCase ( get() ) }

    factory { NotesViewModel ( get() ) }

    factory { CreateNotesViewModel ( get(), get() ) }

    factory { EditNoteViewModel ( get(), get(), get(), get() ) }

    factory { DeleteNoteUseCase ( get() ) }

    factory { UpdateNoteUseCase ( get() ) }

    factory { GetNotesEditUseCase ( get() ) }

    factory { GetUsersUseCase ( get() ) }

    factory { GetUsersCreateUseCase ( get() ) }

    factory { CreateNoteUseCase ( get() ) }

    factory { EditNoteClientImpl ( get()) as EditNoteClientApi}

}
