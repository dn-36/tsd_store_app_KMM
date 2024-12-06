package com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository

import com.project.network.locations_network.model.ResponseItem
import com.project.network.warehouse_network.model.Warehouse

interface WarehouseClientApi {

    suspend fun deleteWarehouse (ui: String, onDelete: () -> Unit)

    suspend fun createWarehouse (name:String, localId:String, onCreate: () -> Unit)

    suspend fun getWarehouse (onGet: (listAllWarehouse: List<Warehouse>) -> Unit)

    suspend fun getLocations (onGet: (listAllLocations: List<ResponseItem>) -> Unit)

    suspend fun updateWarehouse (ui:String, name:String, localId:String, onUpdate: () -> Unit)

}