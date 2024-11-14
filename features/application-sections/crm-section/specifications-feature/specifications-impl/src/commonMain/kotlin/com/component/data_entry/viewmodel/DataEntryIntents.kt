package com.component.data_entry.viewmodel

import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.WarehouseModel

sealed class DataEntryIntents {

    data class SetScreen (val listWarehouse: List<WarehouseModel>,

                          val listProducts: List<ProductResponseModel>,

                          val listCurrency: List<CurrencyResponseModel> ): DataEntryIntents()

    object MenuCurrency: DataEntryIntents()

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