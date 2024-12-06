package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.viewmodel

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

sealed class DataEntryIntents {

    data class SetScreen(

        val description: String,

        val listContragents: List<ContragentResponseArrivalAndConsumption>,

        val listWarehouse: List<WarehouseArrivalAndConsumption>,

        val updatedContragentExpense: ContragentResponseArrivalAndConsumption?,

        val updatedContragentParish: ContragentResponseArrivalAndConsumption?,

        val updatedEntityExpense: ContragentResponseArrivalAndConsumption?,

        val updatedEntityParish: ContragentResponseArrivalAndConsumption?,

        val updatedWarehouse: WarehouseArrivalAndConsumption?

    ) : DataEntryIntents()


    object MenuContragentsParish: DataEntryIntents()

    object MenuContragentsExpense: DataEntryIntents()

    object MenuLegalEntityParish: DataEntryIntents()

    object MenuLegalEntityExpense: DataEntryIntents()

    object MenuWarehouse: DataEntryIntents()


    data class SelectContragentParish ( val item: ContragentResponseArrivalAndConsumption ) : DataEntryIntents()

    data class SelectContragentExpense ( val item: ContragentResponseArrivalAndConsumption) : DataEntryIntents()

    data class SelectLegalEntityParish ( val item: EntityArrivalAndConsumption ) : DataEntryIntents()

    data class SelectLegalEntityExpense ( val item: EntityArrivalAndConsumption ) : DataEntryIntents()

    data class SelectWarehouse ( val item: WarehouseArrivalAndConsumption) : DataEntryIntents()


    object CancelContragentParish: DataEntryIntents()

    object CancelContragentExpense: DataEntryIntents()

    object CancelLegalEntityParish: DataEntryIntents()

    object CancelLegalEntityExpense: DataEntryIntents()

    object CancelWarehouse: DataEntryIntents()


    data class TextInputContragentParish ( val text: String, val newList: List<ContragentResponseArrivalAndConsumption> ): DataEntryIntents()

    data class TextInputContragentExpense ( val text: String, val newList: List<ContragentResponseArrivalAndConsumption> ): DataEntryIntents()

    data class TextInputLegalEntityParish ( val text: String ): DataEntryIntents()

    data class TextInputLegalEntityExpense ( val text: String ): DataEntryIntents()

    data class TextInputDescription ( val text: String ): DataEntryIntents()

    data class TextInputWarehouse ( val text: String, val newList: List<WarehouseArrivalAndConsumption> ): DataEntryIntents()


    object CheckNullTF : DataEntryIntents()

}