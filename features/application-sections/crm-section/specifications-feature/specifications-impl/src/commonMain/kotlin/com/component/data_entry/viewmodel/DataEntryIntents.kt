package com.component.data_entry.viewmodel

import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel

sealed class DataEntryIntents {

    data class SetScreen(

        val item: SpecificResponseModel?,

        val listWarehouse: List<WarehouseModel>,

        val listProducts: List<ProductResponseModel>,

        val listCurrency: List<CurrencyResponseModel>,

        val selectedCurrency: CurrencyResponseModel?,

        val selectedWarehouse: WarehouseModel?,

        val selectedStatus: Pair<String,Int>?,

        val selectedName: String,

    ) : DataEntryIntents()

    object MenuCurrency : DataEntryIntents()

    object MenuWarehouse: DataEntryIntents()

    object MenuStatus: DataEntryIntents()

    data class InputTextCurrency( val text: String,

                                  val list: List<CurrencyResponseModel> ): DataEntryIntents()

    data class InputTextWarehouse( val text: String,

                                   val list: List<WarehouseModel> ): DataEntryIntents()

    data class InputTextName( val text: String ): DataEntryIntents()

    object DeleteSelectedCurrency: DataEntryIntents()

    object DeleteSelectedWarehouse: DataEntryIntents()

    object DeleteSelectedStatus: DataEntryIntents()

    data class SelectedCurrency( val item: CurrencyResponseModel ): DataEntryIntents()

    data class SelectedWarehouse( val item: WarehouseModel ): DataEntryIntents()

    data class SelectedStatus( val name: String, val index:Int ): DataEntryIntents()

}