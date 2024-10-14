package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import com.project.network.organizations_network.model.Response
import com.project.network.warehouse_network.ResponseItem

data class WarehouseState(
    val listAllLocations: List<ResponseItem> = emptyList()
)
