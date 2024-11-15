package com.domain.usecases

import com.domain.repository.LocationsClientApi
import com.model.LocationResponseModel

class GetLocationsUseCase (

    private val client: LocationsClientApi,

    ) {

    suspend fun execute ( ): List<LocationResponseModel> {

        return client.getLocations ( )
    }
}