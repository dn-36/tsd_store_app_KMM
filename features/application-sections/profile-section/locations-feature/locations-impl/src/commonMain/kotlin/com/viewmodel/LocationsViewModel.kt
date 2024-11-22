package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.domain.usecases.CreateLocationUseCase
import com.domain.usecases.DeleteLocationUseCase
import com.domain.usecases.GetContragentUseCase
import com.domain.usecases.GetLocationsUseCase
import com.domain.usecases.UpdateLocationUseCase
import com.model.LocationResponseModel
import com.project.chats.ProfileScreensApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class LocationsViewModel (

    val getLocationsUseCase: GetLocationsUseCase,

    val deleteLocationUseCase: DeleteLocationUseCase,

    val getContragentUseCase: GetContragentUseCase,

    val createLocationUseCase: CreateLocationUseCase,

    val updateLocationUseCase: UpdateLocationUseCase

): NetworkViewModel() {

    var state by mutableStateOf(LocationsState())

    fun processIntents ( intent: LocationsIntents ) {

        when ( intent ) {

            is LocationsIntents.BackFromDataEntry -> backFromDataEntry()

            is LocationsIntents.SetScreen -> {

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    if (state.isSet) {

                        state = state.copy(

                            listLocations = getLocationsUseCase.execute(),

                            listFilteredLocations = getLocationsUseCase.execute(),

                            isSet = false

                        )

                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is LocationsIntents.DeleteLocation -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteLocationUseCase.execute(state.updateLocation!!.id?:0)

                    state = state.copy(

                        isVisibilityDeleteComponent = false,

                        updateLocation = null,

                        listLocations = getLocationsUseCase.execute(),

                        listFilteredLocations = getLocationsUseCase.execute(),

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is LocationsIntents.NoDelete -> noDelete()

            is LocationsIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is LocationsIntents.OpenCreateDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

            intent.coroutineScope.launch ( Dispatchers.IO ) {

                state = state.copy(

                    listContragents = getContragentUseCase.execute(),

                    isVisibilityDataEntry = true

                )

                setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

            }

            }

            is LocationsIntents.OpenUpdateDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    state = state.copy(

                        updateLocation = intent.item,

                        listContragents = getContragentUseCase.execute(),

                        isVisibilityDataEntry = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is LocationsIntents.CreateLocation -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                createLocationUseCase.execute( intent.name, intent.email, intent.phone,

                    intent.default, intent.text, intent.telegram, intent.whatsapp,

                    intent.wechat, intent.point, intent.adres, intent.contragent_id,

                    intent.entity_id, intent.workers, intent.langs)

                    state = state.copy(

                        isVisibilityDataEntry = false,

                        listLocations = getLocationsUseCase.execute(),

                        listFilteredLocations = getLocationsUseCase.execute(),

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is LocationsIntents.UpdateLocation -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    updateLocationUseCase.execute( id = state.updateLocation!!.id?:0,

                    intent.name, intent.email, intent.phone,

                        intent.default, intent.text, intent.telegram, intent.whatsapp,

                        intent.wechat, intent.point, intent.adres, intent.contragent_id,

                        intent.entity_id, intent.workers, intent.langs)

                    state = state.copy(

                        isVisibilityDataEntry = false,

                        updateLocation = null,

                        listLocations = getLocationsUseCase.execute(),

                        listFilteredLocations = getLocationsUseCase.execute(),

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is LocationsIntents.InputTextSearchComponent -> inputTextSearchComponent(intent.text)

            is LocationsIntents.LongPressItem -> longPressItem(intent.index)

            is LocationsIntents.OnePressItem -> onePressItem()

            is LocationsIntents.Back -> back()

        }

    }

    fun back () {

        val profileScreen: ProfileScreensApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(profileScreen.profile())

    }

    fun noDelete () {

        state = state.copy(

            isVisibilityDeleteComponent = false,

            updateLocation = null

        )

    }

    fun openDeleteComponent ( item: LocationResponseModel ) {

    state = state.copy(

        updateLocation = item,

        isVisibilityDeleteComponent = true

    )

    }

    fun backFromDataEntry () {

        state = state.copy(

            isVisibilityDataEntry = false,

            updateLocation = null

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listLocations.filter {

            val companyName = it.name.orEmpty()

            companyName.contains(text, ignoreCase = true)

        }

        state = state.copy(

            listFilteredLocations = newList

        )

        println("Text ${text}")
        println("NewList ${newList}")

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listLocations.size){0F}

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listLocations.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

}