package com.project.tape.domain.usecases

import com.project.tape.domain.repository.TapeClientApi
import com.project.tape.model.ProjectResponseModel
import com.project.tape.model.TapeResponseModel

class GetProjectsUseCase (

    private val client: TapeClientApi,

    ) {

    suspend fun execute ( ): List<ProjectResponseModel> {

        return client.getProjects ( )
    }
}