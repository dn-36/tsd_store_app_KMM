package com.project.project_conterol.domain.usecases

import com.project.project_conterol.domain.repository.ProjectControlClientApi

class UpdateProjectControlUseCase (

    private val client: ProjectControlClientApi,

    ) {

    suspend fun execute ( id:Int, text:String, data: String, time: String,

                          project_id: String ) {

        return client.updateProjectControl(

            id, text, data, time, project_id

        )
    }
}