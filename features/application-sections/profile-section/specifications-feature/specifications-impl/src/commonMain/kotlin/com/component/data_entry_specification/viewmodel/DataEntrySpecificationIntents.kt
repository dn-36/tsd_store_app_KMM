package com.component.data_entry_specification.viewmodel

import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel

sealed class DataEntrySpecificationIntents {

    data class SetScreen(

        val item: SpecificResponseModel?,

        val listWarehouse: List<WarehouseModel>,

        val listProducts: List<ProductResponseModel>,

        val listCurrency: List<CurrencyResponseModel>,

        val selectedCurrency: CurrencyResponseModel?,

        val selectedWarehouse: WarehouseModel?,

        val selectedStatus: Pair<String,Int>?,

        val selectedName: String,

    ) : DataEntrySpecificationIntents()

    object MenuCurrency : DataEntrySpecificationIntents()

    object MenuWarehouse: DataEntrySpecificationIntents()

    object MenuStatus: DataEntrySpecificationIntents()

    data class InputTextCurrency( val text: String,

                                  val list: List<CurrencyResponseModel> ): DataEntrySpecificationIntents()

    data class InputTextWarehouse( val text: String,

                                   val list: List<WarehouseModel> ): DataEntrySpecificationIntents()

    data class InputTextName( val text: String ): DataEntrySpecificationIntents()

    object DeleteSelectedCurrency: DataEntrySpecificationIntents()

    object DeleteSelectedWarehouse: DataEntrySpecificationIntents()

    object DeleteSelectedStatus: DataEntrySpecificationIntents()

    data class SelectedCurrency( val item: CurrencyResponseModel ): DataEntrySpecificationIntents()

    data class SelectedWarehouse( val item: WarehouseModel ): DataEntrySpecificationIntents()

    data class SelectedStatus( val name: String, val index:Int ): DataEntrySpecificationIntents()

}