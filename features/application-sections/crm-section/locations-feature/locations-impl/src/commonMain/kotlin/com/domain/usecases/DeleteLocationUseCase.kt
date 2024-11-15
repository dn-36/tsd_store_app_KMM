package com.domain.usecases

import com.domain.repository.LocationsClientApi
import com.model.LocationResponseModel

class DeleteLocationUseCase (

    private val client: LocationsClientApi,

    ) {

    suspend fun execute ( id:Int ) {

        return client.deleteLocation(id)
    }
}