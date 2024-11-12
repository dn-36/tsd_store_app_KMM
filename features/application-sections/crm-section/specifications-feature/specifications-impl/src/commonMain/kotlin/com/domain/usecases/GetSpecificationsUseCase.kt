package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.SpecificResponseModel

class GetSpecificationsUseCase (

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute ( ): List<SpecificResponseModel> {

        return client.getSpecifications ()

    }
}