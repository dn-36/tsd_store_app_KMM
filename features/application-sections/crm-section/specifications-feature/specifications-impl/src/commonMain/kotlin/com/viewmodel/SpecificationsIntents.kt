package com.viewmodel

import com.model.CurrencyResponseModel
import com.model.ElementSpecification
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel
import kotlinx.coroutines.CoroutineScope

sealed class SpecificationsIntents {

    data class SetScreen( val coroutineScope: CoroutineScope): SpecificationsIntents()

    object Back : SpecificationsIntents()

    object BackFromDataEntry : SpecificationsIntents()

    object BackFromAddProducts : SpecificationsIntents()

    object BackFromListProducts : SpecificationsIntents()

    data class OpenCreateDataEntry ( val coroutineScope: CoroutineScope ) : SpecificationsIntents()
    data class Next(

        val name: String,

        val selectedCurrency: CurrencyResponseModel?,

        val selectedWarehouse: WarehouseModel?,

        val selectedStatus: Int?,

    ) : SpecificationsIntents()

    data class OpenListProducts (val list: List<ElementSpecification>,

                                 val indexMainGroup: Int?,

                                 val byCategory: Float,

        val totalAmount: String )  : SpecificationsIntents()

    data class OpenDeleteComponent ( val item: SpecificResponseModel ): SpecificationsIntents()

    object NoDelete: SpecificationsIntents()

    data class SelectProduct ( val item: ProductResponseModel )  : SpecificationsIntents()

    data class DeleteSpecification ( val coroutineScope: CoroutineScope ) : SpecificationsIntents()

    data class CreateSpecification ( val coroutineScope: CoroutineScope,

                                     val list: List<ElementSpecification> ): SpecificationsIntents()

}