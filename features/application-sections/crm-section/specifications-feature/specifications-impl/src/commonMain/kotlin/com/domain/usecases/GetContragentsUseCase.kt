package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.ContragentResponseModel
import com.model.SpecificResponseModel

class GetContragentsUseCase (

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute ( ): List<ContragentResponseModel> {

        return client.getContragents ()

    }
}