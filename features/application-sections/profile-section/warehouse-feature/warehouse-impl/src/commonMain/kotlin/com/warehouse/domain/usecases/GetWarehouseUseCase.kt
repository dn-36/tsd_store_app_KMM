package com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases

import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi
import com.project.network.warehouse_network.model.Warehouse

class GetWarehouseUseCase (

    private val client: WarehouseClientApi,

    ) {

    suspend fun execute( onGet: (listAllWarehouse: List<Warehouse>) -> Unit

    ){

        client.getWarehouse (

            onGet

        )
    }
}