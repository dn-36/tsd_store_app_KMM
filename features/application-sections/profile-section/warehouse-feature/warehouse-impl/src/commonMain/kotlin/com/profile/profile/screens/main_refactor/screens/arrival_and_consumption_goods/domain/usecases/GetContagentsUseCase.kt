package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.project.network.contragent_network.model.ContragentResponse

class GetContagentsUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute( onGet:( newListContragents: List<ContragentResponse>) -> Unit ) {

        client.getContragents  (
           onGet
        )
    }
}