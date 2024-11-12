package com.project.project_conterol.domain.usecases

import com.project.project_conterol.domain.repository.ProjectControlClientApi

class DeleteProjectControlUseCase (

    private val client: ProjectControlClientApi,

    ) {

    suspend fun execute ( id:Int ) {

        return client.deleteProjectControl(

            id

        )
    }
}