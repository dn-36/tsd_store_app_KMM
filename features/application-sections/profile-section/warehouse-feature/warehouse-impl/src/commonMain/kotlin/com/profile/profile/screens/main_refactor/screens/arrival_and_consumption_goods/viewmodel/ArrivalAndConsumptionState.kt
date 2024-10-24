package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

data class ArrivalAndConsumptionState (

    val listAllWarehouse: List<WarehouseArrivalAndConsumption> = emptyList(),

    val listAllContragent: List<ContragentResponseArrivalAndConsumption> = emptyList(),

    val idLegalEntityParish: Int? = null,

    val idLegalEntityExpense: Int? = null,

    val idContragentExpense: Int? = null,

    val idContragentParish: Int? = null,

    val idWarehouse: Int? = null,

    val isPush: Int = 0,

    val isVisibilityListProducts: MutableState<Float> = mutableStateOf(0f),

    val isVisibilityCountProducts: MutableState<Float> = mutableStateOf(0f),

    val isVisibilityDataEntryComponent: MutableState<Float> = mutableStateOf(0f),

    val isVisibilityAddProductsComponent: MutableState<Float> = mutableStateOf(0f),

    val listProducts:List<AllProductArrivalAndConsumption> = emptyList(),

    val listAllArrivalOrConsumption: List<StoreResponseArrivalAndConsumption> = emptyList(),

    val selectedProduct: ProductArrivalAndConsumption? = null,

    val updatedItem: StoreResponseArrivalAndConsumption? = null,

    val listSelectedProducts:List<ProductArrivalAndConsumption> = emptyList(),

    val updatedContragentExpense : ContragentResponseArrivalAndConsumption? = null,

    val updatedContragentParish : ContragentResponseArrivalAndConsumption? = null,

    val updatedContragentEntityExpense : ContragentResponseArrivalAndConsumption? = null,

    val updatedContragentEntityParish: ContragentResponseArrivalAndConsumption? = null,

    val updatedWarehouse : WarehouseArrivalAndConsumption? = null,

    val isUpdate: Boolean = false

    )