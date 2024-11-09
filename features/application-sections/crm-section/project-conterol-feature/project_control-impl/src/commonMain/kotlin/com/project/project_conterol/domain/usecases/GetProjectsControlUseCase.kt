package com.project.project_conterol.domain.usecases

import com.project.project_conterol.domain.repository.ProjectControlClientApi
import com.project.project_conterol.model.ProjectsControlResponseModel

class GetProjectsControlUseCase (

    private val client: ProjectControlClientApi,

    ) {

    suspend fun execute ( ): ProjectsControlResponseModel {

        return client.getProjectsControl ()
    }
}