package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi

class DeleteArrivalOrConsumptionUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute( ui:String ) {

        client.deleteArrivalOrConsumption  (

          ui
        )
    }
}