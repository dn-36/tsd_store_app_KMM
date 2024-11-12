package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.domain.usecases.GetContragentsUseCase
import com.domain.usecases.GetCurrencyUseCase
import com.domain.usecases.GetProductUseCase
import com.domain.usecases.GetSpecificationsUseCase
import com.domain.usecases.GetWarehouseUseCase
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class SpecificationsViewModel (

    val getSpecificationsUseCase: GetSpecificationsUseCase,

    val getContragentsUseCase: GetContragentsUseCase,

    val getCurrencyUseCase: GetCurrencyUseCase,

    val getWarehouseUseCase: GetWarehouseUseCase,

    val getProductUseCase: GetProductUseCase

): NetworkViewModel() {

    var state by mutableStateOf(SpecificationsState())

    fun processIntents ( intent: SpecificationsIntents ) {

        when (intent) {

            is SpecificationsIntents.SetScreen -> {

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    if ( state.isSet )

                    state = state.copy(

                        listSpecifications = getSpecificationsUseCase.execute(),

                        listContragents = getContragentsUseCase.execute(),

                        isSet = false

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is SpecificationsIntents.Back -> back()

            is SpecificationsIntents.BackFromDataEntry -> backFromDaraEntry()

            is SpecificationsIntents.OpenCreateDataEntry -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    state = state.copy(

                        listCurrency = getCurrencyUseCase.execute(),

                        listWarehouse = getWarehouseUseCase.execute(),

                        listProducts = getProductUseCase.execute(),

                        isVisibilityDataEntry = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

        }

    }

    fun back () {

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

        println("SPECIFICATIONS: ${state.listSpecifications}")
        println("CONTRAGENTS: ${state.listContragents}")

    }

    fun backFromDaraEntry () {

        state = state.copy(

            isVisibilityDataEntry = false

        )

    }

}