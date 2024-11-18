package com.domain.usecases

import com.domain.repository.LocationsClientApi

class DeleteLocationUseCase (

    private val client: LocationsClientApi,

    ) {

    suspend fun execute ( id:Int ) {

        return client.deleteLocation(id)
    }
}