package com.project.project_conterol.domain.repository

import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ProjectsControlResponseModel

interface ProjectControlClientApi {

    suspend fun getProjectsControl(): ProjectsControlResponseModel

    suspend fun getProjects(): List<ProjectResponseModel>

}