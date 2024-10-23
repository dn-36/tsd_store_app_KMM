package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi
import com.project.network.warehouse_network.model.Warehouse

class GetWarehouseArrivalAndConsumptionUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute( onGet: (listAllWarehouse: List<Warehouse>) -> Unit

    ){

        client.getWarehouseArrivalAndConsumption (

            onGet

        )
    }
}