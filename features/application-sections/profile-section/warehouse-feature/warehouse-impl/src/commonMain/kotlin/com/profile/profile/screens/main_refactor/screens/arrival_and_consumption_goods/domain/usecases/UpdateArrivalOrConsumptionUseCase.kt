package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption

class UpdateArrivalOrConsumptionUseCase(

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute(

        productUi: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int,

        listProducts: List<ProductArrivalAndConsumption>

    ) {

        client.updateArrivalOrConsumption(

            productUi,

            idLegalEntityParish,

            idLegalEntityExpense,

            idContragentExpense,

            idContragentParish,

            idWarehouse,

            isPush,

            listProducts,

            )
    }
}