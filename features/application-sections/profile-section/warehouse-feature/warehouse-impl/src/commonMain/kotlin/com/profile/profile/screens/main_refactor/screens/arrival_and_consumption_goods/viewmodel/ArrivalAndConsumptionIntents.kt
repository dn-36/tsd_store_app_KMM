package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import kotlinx.coroutines.CoroutineScope

sealed class ArrivalAndConsumptionIntents {
    data class Arrival(val coroutineScope: CoroutineScope) : ArrivalAndConsumptionIntents()
    object BackDataEntry : ArrivalAndConsumptionIntents()
    object BackAddProducts : ArrivalAndConsumptionIntents()
    data class Next(

        val idLegalEntityParish: Int?,

        val idLegalEntityExpense: Int?,

        val idContragentExpense: Int?,

        val idContragentParish: Int?,

        val idWarehouse: Int?

    ) : ArrivalAndConsumptionIntents()

    object BackListProducts : ArrivalAndConsumptionIntents()

    data class SelectProducts(val selectedProducts: ProductArrivalAndConsumption) :
        ArrivalAndConsumptionIntents()

    data class Ready(val count: Int) : ArrivalAndConsumptionIntents()

    data class SelectFromList(val coroutineScope: CoroutineScope) : ArrivalAndConsumptionIntents()
    data class CreateArrivalOrConsumption (val coroutineScope: CoroutineScope) : ArrivalAndConsumptionIntents()
    data class GetArrivalAndConsumptionGoods(val coroutineScope: CoroutineScope) :
        ArrivalAndConsumptionIntents()

    object Scanner : ArrivalAndConsumptionIntents()

}