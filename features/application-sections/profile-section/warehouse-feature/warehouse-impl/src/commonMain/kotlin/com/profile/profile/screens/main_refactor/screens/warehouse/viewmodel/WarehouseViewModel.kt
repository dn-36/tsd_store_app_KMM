package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.profile.profile.screens.main_refactor.screens.warehouse.component.CreateWarehouseComponent
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.CreateWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.DeleteWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.GetLocationsUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.GetWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.UpdateWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.screen.WarehouseScreen
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class WarehouseViewModel (

    val createWarehouseUseCase: CreateWarehouseUseCase,

    val deleteWarehouseUseCase: DeleteWarehouseUseCase,

    val updateWarehouseUseCase: UpdateWarehouseUseCase,

    val getWarehouseUseCase: GetWarehouseUseCase,

    val getLocationsUseCase: GetLocationsUseCase,

    ) : NetworkViewModel() {

    var state by mutableStateOf(WarehouseState())

    fun processIntents(intent: WarehouseIntents) {

        when (intent) {

            is WarehouseIntents.OpenWindowAddWarehouse -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getLocationsUseCase.execute ( onGet = {listAllLocations ->

                        state = state.copy(

                            listAllLocations = listAllLocations,
                            index = 2

                        )

                        Navigation.navigator.push(
                            CreateWarehouseComponent(listAllLocations = state.listAllLocations,
                                onClickCreate = { scope, name, localId ->
                                    processIntents(
                                        WarehouseIntents.CreateWarehouse(
                                            scope,
                                            name,
                                            localId
                                        )
                                    )
                                }, onClickUpdate = {scope, name, localId, ui ->  }

                                , warehouse = null, index = state.index,

                                locationUpdated = null,

                                onClickCansel = { processIntents(WarehouseIntents.CanselComponent)}))


                    } )

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }

            }

            is WarehouseIntents.OpenWindowUpdateWarehouse -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getLocationsUseCase.execute ( onGet = { listAllLocations ->

                        state = state.copy(

                            listAllLocations = listAllLocations,
                            index = 1

                        )

                        val locations = listAllLocations.find { it.adres == intent.warehouse.stores[0]!!.local!!.adres}

                        Navigation.navigator.push(
                            CreateWarehouseComponent(listAllLocations = state.listAllLocations,
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

                                , warehouse = intent.warehouse, index = state.index,

                                locationUpdated = locations,

                                onClickCansel = { processIntents(WarehouseIntents.CanselComponent)})
                        )

                    },
                    )

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }

            }

            is WarehouseIntents.SetScreen -> {

                if(state.isUsed.value) {

                    state.isUsed.value = false

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        getWarehouseUseCase.execute(onGet = { listAllWarehouse ->

                            state = state.copy(

                                listAllWarehouse = listAllWarehouse,

                                )

                        }
                        )

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }
                }

            }

            is WarehouseIntents.CreateWarehouse -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                if ( intent.name.isNotBlank() && intent.localId.isNotBlank() ) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        createWarehouseUseCase.execute(intent.name, intent.localId, onCreate = {

                            Navigation.navigator.push(WarehouseScreen())

                        })

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }

                }

            }

            is WarehouseIntents.DeleteWarehouse -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteWarehouseUseCase.execute( state.updateUiWarehouse, onDelete = {

                        state = state.copy(

                            isUsed = mutableStateOf(true),

                            isVisibilityDeleteComponent = 0f

                            )

                    })

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }


            }

            is WarehouseIntents.UpdateWarehouse -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                if (intent.name.isNotBlank() && intent.localId.isNotBlank()) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        updateWarehouseUseCase.execute(intent.ui, intent.name, intent.localId,

                            onUpdate = { Navigation.navigator.push(WarehouseScreen()) })

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }

                }
            }

            is WarehouseIntents.CanselComponent -> { cansel() }

            is WarehouseIntents.NoDelete -> { noDelete() }

            is WarehouseIntents.OpenDeleteComponent -> { openDeleteComponent( intent.ui ) }

        }

    }

    fun cansel () {

        Navigation.navigator.push(WarehouseScreen())

    }

    fun noDelete () {

        state = state.copy(

            isVisibilityDeleteComponent = 0f,

            updateUiWarehouse = ""

        )

    }

    fun openDeleteComponent ( ui: String ) {

        state = state.copy(

            isVisibilityDeleteComponent = 1f,

            updateUiWarehouse = ui

        )

    }

}