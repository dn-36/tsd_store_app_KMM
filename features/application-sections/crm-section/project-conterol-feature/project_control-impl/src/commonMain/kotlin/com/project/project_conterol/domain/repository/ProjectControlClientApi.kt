package com.project.project_conterol.domain.repository

import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ProjectsControlResponseModel

interface ProjectControlClientApi {

    suspend fun getProjectsControl(): ProjectsControlResponseModel

    suspend fun getProjects(): List<ProjectResponseModel>

    suspend fun createProjectControl( text:String, data: String, time: String, project_id: String )
    suspend fun updateProjectControl( id: Int,text:String, data: String, time: String,

                                      project_id: String )

    suspend fun deleteProjectControl( id: Int )

}