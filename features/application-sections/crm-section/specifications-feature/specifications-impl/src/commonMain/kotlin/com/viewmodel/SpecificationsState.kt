package com.viewmodel

import com.model.ContragentResponseModel
import com.model.CurrencyResponseModel
import com.model.ElementSpecification
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel

data class SpecificationsState(

    val listSpecifications: List<SpecificResponseModel> = emptyList(),

    val listFilteredSpecifications: List<SpecificResponseModel> = emptyList(),

    val listContragents: List<ContragentResponseModel> = emptyList(),

    val listCurrency: List<CurrencyResponseModel> = emptyList(),

    val listWarehouse: List<WarehouseModel> = emptyList(),

    val listProducts: List<ProductResponseModel> = emptyList(),

    val listElementsSpecifications: List<ElementSpecification> = emptyList(),

    val selectedCurrency: CurrencyResponseModel? = null,

    val selectedWarehouse: WarehouseModel? = null,

    val selectedStatus: Pair<String,Int>? = null,

    val indexMainGroup: Int? = null,

    val byCategory: Float = 1f,

    val name: String = "",

    val isSet: Boolean = true,

    val isVisibilityDataEntry: Boolean = false,

    val isVisibilityAddProducts: Boolean = false,

    val isVisibilityListProducts: Boolean = false,

    val isVisibilityDeleteComponent: Boolean = false,

    val updateItem: SpecificResponseModel? = null

    )
