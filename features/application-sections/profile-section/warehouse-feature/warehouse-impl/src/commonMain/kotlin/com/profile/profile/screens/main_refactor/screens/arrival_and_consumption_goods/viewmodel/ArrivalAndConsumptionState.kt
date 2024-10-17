package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.model.Warehouse
import product_network.model.Product

data class ArrivalAndConsumptionState (

    val listAllWarehouse: List<Warehouse> = emptyList(),

    val listAllContragent: List<ContragentResponse> = emptyList(),

    val filteredContragentParish: List<ContragentResponse> = emptyList(),

    val filteredContragentExpense: List<ContragentResponse> = emptyList(),

    val legalEntityParish: List<ContragentResponse> = emptyList(),

    val legalEntityExpense: List<ContragentResponse> = emptyList(),

    val listAllProducts: List<Product> = emptyList(),

    val selectedProducts: List<ProductArrivalAndConsumption> = emptyList() // Массив продуктов для отправки

    )