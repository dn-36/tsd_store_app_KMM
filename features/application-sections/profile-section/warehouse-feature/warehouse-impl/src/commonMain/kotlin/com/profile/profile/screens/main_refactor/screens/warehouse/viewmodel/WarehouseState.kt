package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import com.project.network.notes_network.model.User
import com.project.network.warehouse_network.model.ResponseItem

data class WarehouseState(
    val listAllLocations: List<ResponseItem> = emptyList(),
    val filteredLocations: List<ResponseItem> = emptyList(),

    )
