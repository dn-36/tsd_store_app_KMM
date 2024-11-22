package com.domain.usecases

import com.domain.repository.LocationsClientApi
import com.model.ContragentsResponseModel

class GetContragentUseCase (

    private val client: LocationsClientApi,

    ) {

    suspend fun execute ( ): List<ContragentsResponseModel> {

        return client.getContragent ( )
    }
}