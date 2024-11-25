package com.project.tape.domain.usecases

import com.project.tape.domain.repository.TapeClientApi
import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectResponseModel

class GetContragentsUseCase (

    private val client: TapeClientApi,

    ) {

    suspend fun execute ( ): List<ContragentsResponseModel> {

        return client.getContragents ( )
    }
}