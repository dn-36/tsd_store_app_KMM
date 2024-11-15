package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.domain.usecases.DeleteLocationUseCase
import com.domain.usecases.GetLocationsUseCase
import com.model.LocationResponseModel
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class LocationsViewModel (

    val getLocationsUseCase: GetLocationsUseCase,

    val deleteLocationUseCase: DeleteLocationUseCase

): NetworkViewModel() {

    var state by mutableStateOf(LocationsState())

    fun processIntents ( intent: LocationsIntents ) {

        when ( intent ) {

            is LocationsIntents.Back -> back()

            is LocationsIntents.BackFromDataEntry -> backFromDataEntry()

            is LocationsIntents.SetScreen -> {

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    if (state.isSet) {

                        state = state.copy(

                            listLocations = getLocationsUseCase.execute(),

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

                        listLocations = getLocationsUseCase.execute()

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is LocationsIntents.NoDelete -> noDelete()

            is LocationsIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is LocationsIntents.OpenCreateDataEntryComponent -> {

            intent.coroutineScope.launch ( Dispatchers.IO ) {

                state = state.copy(

                    isVisibilityDataEntry = true

                )

            }

            }

        }

    }

    fun back () {

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

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

            isVisibilityDataEntry = false

        )

    }

}