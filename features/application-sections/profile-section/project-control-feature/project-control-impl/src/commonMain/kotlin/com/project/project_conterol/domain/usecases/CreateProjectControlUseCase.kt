package com.project.project_conterol.domain.usecases

import com.project.project_conterol.domain.repository.ProjectControlClientApi
import com.project.project_conterol.model.ProjectsControlResponseModel

class CreateProjectControlUseCase (

    private val client: ProjectControlClientApi,

    ) {

    suspend fun execute ( text:String, data: String, time: String,

                          project_id: String ) {

        return client.createProjectControl(

            text, data, time, project_id

        )
    }
}