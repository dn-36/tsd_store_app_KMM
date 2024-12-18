package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import kotlinx.coroutines.CoroutineScope

sealed class ArrivalAndConsumptionIntents {

    data class ArrivalOrConsumption (val coroutineScope: CoroutineScope, val isPush: Int ) : ArrivalAndConsumptionIntents()
    object BackFromDataEntry : ArrivalAndConsumptionIntents()

    object BackFromAddProducts : ArrivalAndConsumptionIntents()
    object NoDelete : ArrivalAndConsumptionIntents()
    data class OpenDeleteComponent ( val item: StoreResponseArrivalAndConsumption? )

        : ArrivalAndConsumptionIntents()

    data class Next(

        val description: String,

        val idLegalEntityParish: Int?,

        val idLegalEntityExpense: Int?,

        val idContragentExpense: Int?,

        val idContragentParish: Int?,

        val idWarehouse: Int?

    ) : ArrivalAndConsumptionIntents()

    object BackFromListProducts : ArrivalAndConsumptionIntents()

    data class SelectProducts(val selectedProducts: ProductArrivalAndConsumption) :

        ArrivalAndConsumptionIntents()

    data class Ready( val count: String ) : ArrivalAndConsumptionIntents()
    object  CanselScanner : ArrivalAndConsumptionIntents()
    data class AddProductScanner( val name: String ) : ArrivalAndConsumptionIntents()

    data class OpenUpdateDataEntry(

        val coroutineScope: CoroutineScope,

        val item: StoreResponseArrivalAndConsumption

    ) : ArrivalAndConsumptionIntents()

    data class Update(val coroutineScope: CoroutineScope) : ArrivalAndConsumptionIntents()

    object SelectFromList : ArrivalAndConsumptionIntents()

    data class CreateArrivalOrConsumption(val coroutineScope: CoroutineScope) :
        ArrivalAndConsumptionIntents()

    data class DeleteArrivalOrConsumption(val coroutineScope: CoroutineScope ) :

        ArrivalAndConsumptionIntents()

    data class GetArrivalAndConsumptionGoods(val coroutineScope: CoroutineScope) :

        ArrivalAndConsumptionIntents()

    object Scanner : ArrivalAndConsumptionIntents()

    data class CanselSelectedProduct ( val index: Int) : ArrivalAndConsumptionIntents()

    data class LongPressItem( val index: Int ): ArrivalAndConsumptionIntents()
    object OnePressItem: ArrivalAndConsumptionIntents()


    data class InputTextSearchComponent( val text: String ): ArrivalAndConsumptionIntents()


}