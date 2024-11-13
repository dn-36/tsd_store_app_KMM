package com.component.data_entry.viewmodel

import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.WarehouseModel

data class DataEntryState(

    val currency: String = "",

    val warehouse: String = "",

    val filteredListWarehouse: List<WarehouseModel> = emptyList(),

    val filteredListProducts: List<ProductResponseModel> = emptyList(),

    val filteredListCurrency: List<CurrencyResponseModel> = emptyList(),

    val isSet: Boolean = true,

    val selectedCurrency: CurrencyResponseModel? = null,

    val selectedWarehouse: WarehouseModel? = null,

    val selectedStatus: Pair<String,Int>? = null,

    val expendedCurrency: Boolean = false,

    val expendedWarehouse: Boolean = false,

    val expendedStatus: Boolean = false,

    )
