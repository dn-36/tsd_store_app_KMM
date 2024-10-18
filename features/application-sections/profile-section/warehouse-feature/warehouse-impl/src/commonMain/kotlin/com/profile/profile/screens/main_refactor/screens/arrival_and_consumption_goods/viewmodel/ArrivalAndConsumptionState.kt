package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.project.network.arrival_goods.model.StoreResponse
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.model.Warehouse
import product_network.model.Product

data class ArrivalAndConsumptionState (

    val listAllWarehouse: List<Warehouse> = emptyList(),

    val listAllContragent: List<ContragentResponse> = emptyList(),

    val filteredContragentParish: List<ContragentResponse> = emptyList(),

    val filteredContragentExpense: List<ContragentResponse> = emptyList(),

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

    val listProducts:List<Product> = emptyList(),

    val listAllArrivalOrConsumption: List<StoreResponse> = emptyList(),

    val selectedProduct: ProductArrivalAndConsumption? = null,

    val listSelectedProducts:List<ProductArrivalAndConsumption> = emptyList()


    )