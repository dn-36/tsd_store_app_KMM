package com.project.tape

import com.project.menu.screen.TapeScreenApi
import com.project.network.contragent_network.ContragentClient
import com.project.network.projects_network.ProjectClient
import com.project.network.tape_network.TapeClient
import com.project.tape.datasource.TapeClientImpl
import com.project.tape.domain.repository.TapeClientApi
import com.project.tape.domain.usecases.CreatePhotoOrVideoUseCase
import com.project.tape.domain.usecases.GetContragentsUseCase
import com.project.tape.domain.usecases.GetProjectsUseCase
import com.project.tape.domain.usecases.GetVideoUseCase
import com.project.tape.viewmodel.TapeViewModel
import org.koin.dsl.module


val tapeScreenModule = module {

    factory { TapeScreenImpl() as TapeScreenApi }

    factory { TapeClientImpl(get(), get(), get(), get()) as TapeClientApi }

    factory { GetVideoUseCase(get() ) }

    factory { GetProjectsUseCase(get() ) }

    factory { GetContragentsUseCase(get() ) }

    factory { CreatePhotoOrVideoUseCase(get() ) }

    factory { TapeViewModel(get(), get(), get(), get() ) }

    factory { TapeClient() }

    factory { ProjectClient() }

    factory { ContragentClient() }

}