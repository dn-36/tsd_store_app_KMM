package com.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption

class CreateArrivalOrConsumptionUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute(

        description: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int,

        listProducts: List<ProductArrivalAndConsumption>
    ) {

        client.createArrivalOrConsumption (

        description,

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