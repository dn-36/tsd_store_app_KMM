package com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases

import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi
import com.project.network.locations_network.model.ResponseItem
import com.project.network.warehouse_network.model.Warehouse

class GetLocationsUseCase (

    private val client: WarehouseClientApi,

    ) {

    suspend fun execute( onGet: (listAllLocations: List<ResponseItem>) -> Unit

    ){

        client.getLocations (

            onGet

        )
    }
}