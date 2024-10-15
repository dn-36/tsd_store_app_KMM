package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.warehouse.component.CreateWarehouseComponent
import com.profile.profile.screens.main_refactor.screens.warehouse.screen.WarehouseScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.locations_network.LocationsApi
import com.project.network.warehouse_network.WarehouseApi
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class WarehouseViewModel : ViewModel() {

    var warehouseState by mutableStateOf(WarehouseState())

    fun processIntents(intent: WarehouseIntents) {
        when (intent) {

            is WarehouseIntents.OpenWindowAddWarehouse -> {

                openWindowAddWarehouse(intent.coroutineScope)

            }

            is WarehouseIntents.OpenWindowUpdateWarehouse -> {

                openWindowUpdateWarehouse(intent.coroutineScope,intent.warehouse)

            }

            is WarehouseIntents.SetScreen -> {

                setScreen(intent.coroutineScope)

            }

            is WarehouseIntents.CreateWarehouse -> {

                createWarehouse(intent.coroutineScope, intent.name, intent.localId)

            }

            is WarehouseIntents.DeleteWarehouse -> {

                deleteWarehouse(intent.coroutineScope, intent.ui)

            }

            is WarehouseIntents.UpdateWarehouse -> {

                updateWarehouse(intent.coroutineScope, intent.ui, intent.name, intent.localId)

            }

        }
    }

    fun openWindowAddWarehouse(coroutineScope: CoroutineScope) {

        coroutineScope.launch(Dispatchers.IO) {

            val token = ConstData.TOKEN

            LocationsApi.token = token

            val newListLocations = LocationsApi.getLocations()

            warehouseState = warehouseState.copy(

                listAllLocations = newListLocations,
                index = 2

            )
            println("Warehouse:${newListLocations}")

            Navigation.navigator.push(
                CreateWarehouseComponent(listAllLocations = warehouseState.listAllLocations,
                    onClickCreate = { scope, name, localId ->
                        processIntents(
                            WarehouseIntents.CreateWarehouse(
                                scope,
                                name,
                                localId
                            )
                        )
                    }, onClickUpdate = {scope, name, localId, ui ->  }
                , warehouse = null, index = warehouseState.index, locationUpdated = null)
            )
        }

    }

    fun openWindowUpdateWarehouse(coroutineScope: CoroutineScope,warehouse: Warehouse) {

        coroutineScope.launch(Dispatchers.IO) {

            val token = ConstData.TOKEN

            LocationsApi.token = token

            val newListLocations = LocationsApi.getLocations()

            warehouseState = warehouseState.copy(

                listAllLocations = newListLocations,
                index = 1

            )

            val locations = newListLocations.find { it.adres == warehouse.stores[0]!!.local!!.adres}

            Navigation.navigator.push(
                CreateWarehouseComponent(listAllLocations = warehouseState.listAllLocations,
                    onClickUpdate = { scope, name, localId,ui ->
                        processIntents(
                            WarehouseIntents.UpdateWarehouse(
                                scope,
                                ui,
                                name,
                                localId
                            )
                        )
                    }, onClickCreate = {scope, name, localId ->  }
                    , warehouse = warehouse, index = warehouseState.index, locationUpdated = locations)
            )
        }

    }

    fun createWarehouse(coroutineScope: CoroutineScope,name:String,localId:String){

        val token = ConstData.TOKEN

        WarehouseApi.token = token

        coroutineScope.launch (Dispatchers.IO) {

        WarehouseApi.createWarehouse(name,localId)

            Navigation.navigator.push(WarehouseScreen())

        }
    }

    fun updateWarehouse(coroutineScope: CoroutineScope,ui:String,name:String,localId:String){

        val token = ConstData.TOKEN

        WarehouseApi.token = token

        coroutineScope.launch (Dispatchers.IO) {

            WarehouseApi.updateWarehouse(ui,name,localId)

            Navigation.navigator.push(WarehouseScreen())

        }
    }

    fun deleteWarehouse(coroutineScope: CoroutineScope,ui:String){

        val token = ConstData.TOKEN

        WarehouseApi.token = token

        coroutineScope.launch (Dispatchers.IO) {

            WarehouseApi.deleteWarehouse(ui)

            warehouseState = warehouseState.copy(

                isUsed = mutableStateOf(true),

                )

        }
    }

    fun setScreen(coroutineScope: CoroutineScope){

        if(warehouseState.isUsed.value){

            warehouseState.isUsed.value = false

            val token = ConstData.TOKEN

            WarehouseApi.token = token

            coroutineScope.launch(Dispatchers.IO) {

                val newListWarehouse = WarehouseApi.getWarehouse()

                warehouseState = warehouseState.copy(

                    listAllWarehouse = newListWarehouse,

                    )
                println("Warehouse:${newListWarehouse}")
            }

        }

    }

}