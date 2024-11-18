package com.component.data_entry_specification.viewmodel

import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.WarehouseModel

data class DataEntrySpecificationState(

    val currency: String = "",

    val warehouse: String = "",

    val name: String = "",

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
