package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import kotlinx.coroutines.CoroutineScope

sealed class ArrivalAndConsumptionIntents {

    data class ArrivalOrConsumption (val coroutineScope: CoroutineScope, val isPush: Int ) : ArrivalAndConsumptionIntents()
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

    data class Ready( val count: String ) : ArrivalAndConsumptionIntents()

    data class UpdateButton(

        val coroutineScope: CoroutineScope,

        val item: StoreResponseArrivalAndConsumption

    ) : ArrivalAndConsumptionIntents()

    data class Update(val coroutineScope: CoroutineScope) : ArrivalAndConsumptionIntents()

    data class SelectFromList(val coroutineScope: CoroutineScope) : ArrivalAndConsumptionIntents()

    data class CreateArrivalOrConsumption(val coroutineScope: CoroutineScope) :
        ArrivalAndConsumptionIntents()

    data class DeleteArrivalOrConsumption(val coroutineScope: CoroutineScope, val ui: String) :
        ArrivalAndConsumptionIntents()

    data class GetArrivalAndConsumptionGoods(val coroutineScope: CoroutineScope) :

        ArrivalAndConsumptionIntents()

    object Scanner : ArrivalAndConsumptionIntents()

    data class CanselSelectedProduct ( val index: Int) : ArrivalAndConsumptionIntents()

}