package com.profile.profile.screens.main_refactor.screens.warehouse.datasource

import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi
import com.project.network.locations_network.LocationsClient
import com.project.network.locations_network.model.ResponseItem
import com.project.network.organizations_network.OrganizationsClient
import com.project.network.warehouse_network.WarehouseClient
import com.project.network.warehouse_network.model.Warehouse

class WarehouseClientImpl (

    private val warehouseClient: WarehouseClient,

    private val locationsClient: LocationsClient

) : WarehouseClientApi {

    override suspend fun deleteWarehouse(ui: String, onDelete: () -> Unit) {

        warehouseClient.deleteWarehouse(ui)

        onDelete()

    }

    override suspend fun updateWarehouse( ui:String, name:String, localId:String, onUpdate: () -> Unit ) {

        warehouseClient.updateWarehouse( ui, name, localId )

        onUpdate()

    }

    override suspend fun createWarehouse( name:String, localId:String, onCreate: () -> Unit ) {

        warehouseClient.createWarehouse( name, localId )

        onCreate()

    }

    override suspend fun getWarehouse( onGet: (listAllWarehouse: List<Warehouse>) -> Unit ) {

       val allWarehouse = warehouseClient.getWarehouse()

        onGet( allWarehouse )

    }

    override suspend fun getLocations( onGet: (listAllLocations: List<ResponseItem>) -> Unit ) {

        val allLocations = locationsClient.getLocations()

        onGet( allLocations )

    }

}