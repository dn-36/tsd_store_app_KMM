package com.project.project_conterol.domain.usecases

import com.project.project_conterol.domain.repository.ProjectControlClientApi
import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ProjectsControlResponseModel

class GetProjectsUseCase (

    private val client: ProjectControlClientApi,

    ) {

    suspend fun execute ( ): List<ProjectResponseModel> {

        return client.getProjects ()
    }
}