package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.project.network.locations_network.model.ResponseItem
import com.project.network.warehouse_network.model.Warehouse

data class WarehouseState(

    val listAllLocations: List<ResponseItem> = emptyList(),

    val filteredLocations: List<ResponseItem> = emptyList(),

    val listAllWarehouse: List<Warehouse> = emptyList(),

    val listColorsSelectedWarehouse: MutableList<Color> = mutableListOf(),

    val isUsed: MutableState<Boolean> = mutableStateOf(true),

    val index: Int = 0,

    val textButtonWindow: String = "",

    )
