package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.project.network.contragent_network.model.ContragentResponse

class CreateArrivalOrConsumptionUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute(  idLegalEntityParish: Int?,

                          idLegalEntityExpense: Int?,

                          idContragentExpense: Int?,

                          idContragentParish: Int?,

                          idWarehouse: Int?,

                          isPush: Int ,

                         listProducts:List<ProductArrivalAndConsumption>) {

        client.createArrivalOrConsumption (

        idLegalEntityParish ,

        idLegalEntityExpense ,

        idContragentExpense ,

        idContragentParish ,

        idWarehouse ,

        isPush ,

        listProducts ,

        )
    }
}