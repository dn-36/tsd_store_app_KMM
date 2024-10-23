package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.warehouse.component.CreateWarehouseComponent
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.CreateWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.DeleteWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.GetLocationsUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.GetWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.UpdateWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.screen.WarehouseScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.locations_network.LocationsClient
import com.project.network.warehouse_network.WarehouseClient
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class WarehouseViewModel (

    val createWarehouseUseCase: CreateWarehouseUseCase,

    val deleteWarehouseUseCase: DeleteWarehouseUseCase,

    val updateWarehouseUseCase: UpdateWarehouseUseCase,

    val getWarehouseUseCase: GetWarehouseUseCase,

    val getLocationsUseCase: GetLocationsUseCase,

    ) : ViewModel() {

    var warehouseState by mutableStateOf(WarehouseState())

    fun processIntents(intent: WarehouseIntents) {

        when (intent) {

            is WarehouseIntents.OpenWindowAddWarehouse -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getLocationsUseCase.execute ( onGet = {listAllLocations ->

                        warehouseState = warehouseState.copy(

                            listAllLocations = listAllLocations,
                            index = 2

                        )

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

                    } )

                }

                //openWindowAddWarehouse(intent.coroutineScope)

            }

            is WarehouseIntents.OpenWindowUpdateWarehouse -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getLocationsUseCase.execute ( onGet = { listAllLocations ->

                        warehouseState = warehouseState.copy(

                            listAllLocations = listAllLocations,
                            index = 1

                        )

                        val locations = listAllLocations.find { it.adres == intent.warehouse.stores[0]!!.local!!.adres}

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
                                , warehouse = intent.warehouse, index = warehouseState.index, locationUpdated = locations)
                        )

                    },
                    )


                }

                //openWindowUpdateWarehouse(intent.coroutineScope,intent.warehouse)

            }

            is WarehouseIntents.SetScreen -> {

                if(warehouseState.isUsed.value) {

                    warehouseState.isUsed.value = false

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        getWarehouseUseCase.execute(onGet = { listAllWarehouse ->

                            warehouseState = warehouseState.copy(

                                listAllWarehouse = listAllWarehouse,

                                )

                        }
                        )

                    }
                }

                //setScreen(intent.coroutineScope)

            }

            is WarehouseIntents.CreateWarehouse -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    createWarehouseUseCase.execute(intent.name, intent.localId, onCreate = {

                        Navigation.navigator.push(WarehouseScreen())

                    })
                }
                //createWarehouse(intent.coroutineScope, intent.name, intent.localId)

            }

            is WarehouseIntents.DeleteWarehouse -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteWarehouseUseCase.execute(intent.ui, onDelete = {

                        warehouseState = warehouseState.copy(

                            isUsed = mutableStateOf(true),

                            )

                    })
                }
                //deleteWarehouse(intent.coroutineScope, intent.ui)

            }

            is WarehouseIntents.UpdateWarehouse -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    updateWarehouseUseCase.execute( intent.ui, intent.name, intent.localId ,

                        onUpdate = { Navigation.navigator.push(WarehouseScreen()) })
                }

                //updateWarehouse(intent.coroutineScope, intent.ui, intent.name, intent.localId)

            }

        }
    }

}