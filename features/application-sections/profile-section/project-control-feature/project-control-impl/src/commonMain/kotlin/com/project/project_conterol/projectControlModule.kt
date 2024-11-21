package com.project.project_conterol

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.`menu-crm-api`.ProjectControlScreenApi
import com.project.network.projects_control_network.ProjectControlClient
import com.project.network.projects_network.ProjectsClient
import com.project.project_conterol.datasource.ProjectsControlClientImpl
import com.project.project_conterol.domain.repository.ProjectControlClientApi
import com.project.project_conterol.domain.usecases.CreateProjectControlUseCase
import com.project.project_conterol.domain.usecases.DeleteProjectControlUseCase
import com.project.project_conterol.domain.usecases.GetProjectsControlUseCase
import com.project.project_conterol.domain.usecases.GetProjectsUseCase
import com.project.project_conterol.domain.usecases.UpdateProjectControlUseCase
import org.example.project.presentation.project_control.viewmodel.ProjectControlViewModel
import org.koin.dsl.module

val projectControlModule = module {

    factory { ProjectControlImpl() as ProjectControlScreenApi }

    factory { GetProjectsControlUseCase( get()) }

    factory { CreateProjectControlUseCase( get()) }

    factory { UpdateProjectControlUseCase( get()) }

    factory { DeleteProjectControlUseCase( get()) }

    factory { GetProjectsUseCase( get()) }

    factory { ProjectsControlClientImpl( get(), get(), get()) as ProjectControlClientApi }

    factory { ProjectControlClient() }

    factory { ProjectsClient() }

    factory { ProjectControlViewModel( get(), get(), get(), get(), get()) }

}