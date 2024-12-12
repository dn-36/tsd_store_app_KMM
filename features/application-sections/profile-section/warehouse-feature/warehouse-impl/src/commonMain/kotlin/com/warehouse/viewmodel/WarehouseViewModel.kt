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
import com.project.chats.ProfileScreensApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

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

                            listAllLocations = listAllLocations

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

                                , warehouse = null,

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

                            listAllLocations = listAllLocations

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

                                , warehouse = intent.warehouse,

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

                                listWarehouse = listAllWarehouse,

                                listFilteredWarehouse = listAllWarehouse

                                )

                        }
                        )

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }
                }

            }

            is WarehouseIntents.CreateWarehouse -> {

                if ( intent.name.isNotBlank() && intent.localId.isNotBlank() ) {

                    setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

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

            is WarehouseIntents.InputTextSearchComponent -> inputTextSearchComponent(intent.text)

            is WarehouseIntents.LongPressItem -> longPressItem(intent.index)

            is WarehouseIntents.OnePressItem -> onePressItem()

            is WarehouseIntents.Back -> back()

        }

    }

    fun back () {

        val profileScreen: ProfileScreensApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(profileScreen.profile())

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

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listWarehouse.map { warehouse ->
            // Фильтруем список stores внутри каждого warehouse

            val filteredStores = warehouse.stores.filter {

                val companyName = it?.name.orEmpty() // Безопасный доступ к name
                //Если it или it.name равны null, используется пустая строка (orEmpty()),
                // чтобы избежать ошибок при вызове contains.

                companyName.contains(text, ignoreCase = true)
            }
            // Возвращаем новый Warehouse с обновлённым списком stores

            warehouse.copy(stores = filteredStores)

        }.filter { it.stores.isNotEmpty() }

        state = state.copy(

                listFilteredWarehouse = newList

            )

            println("Text ${text}")

            println("NewList ${newList}")

    }

    fun longPressItem ( index: Int ) {

        val totalStoresCount = state.listWarehouse.flatMap { it.stores }.size

        val newList = MutableList(totalStoresCount) { 0F }

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

        println("SIZE ${newList.size}")

    }

    fun onePressItem () {

        val newList = MutableList(state.listWarehouse.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

}