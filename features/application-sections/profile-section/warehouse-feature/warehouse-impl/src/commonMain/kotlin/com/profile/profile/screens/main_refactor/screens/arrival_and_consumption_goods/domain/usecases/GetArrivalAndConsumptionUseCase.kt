package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption

class GetArrivalAndConsumptionUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute(): List<StoreResponseArrivalAndConsumption> {

       return client.getArrivalAndConsumption  ()
    }
}