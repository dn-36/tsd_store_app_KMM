package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.model.Warehouse

data class ArrivalAndConsumptionState (

    val listAllWarehouse: List<Warehouse> = emptyList(),

    val listAllContragent: List<ContragentResponse> = emptyList(),

    val filteredContragentParish: List<ContragentResponse> = emptyList(),

    val filteredContragentExpense: List<ContragentResponse> = emptyList(),

    val legalEntityParish: List<ContragentResponse> = emptyList(),

    val legalEntityExpense: List<ContragentResponse> = emptyList()

    )