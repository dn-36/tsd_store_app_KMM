package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.domain.usecases.GetContragentsUseCase
import com.domain.usecases.GetCurrencyUseCase
import com.domain.usecases.GetProductUseCase
import com.domain.usecases.GetSpecificationsUseCase
import com.domain.usecases.GetWarehouseUseCase
import com.model.CurrencyResponseModel
import com.model.ElementSpecification
import com.model.ProductResponseModel
import com.model.WarehouseModel
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

                        isSet = false,

                        listElementsSpecifications = listOf( ElementSpecification(

                            product = mutableListOf(
                                ProductResponseModel(

                                    id = null,
                                    name = "Имя продукта",
                                    sku = null,
                                    ui = null,
                                    price = null,
                                    category = null

                                )
                            ),
                            count = mutableListOf(""),
                            block = "Имя группы",
                            price_item = mutableListOf(""),
                            nds = mutableListOf(""),
                            spectext = mutableListOf(""),
                            totalPrice = mutableListOf("")

                        ))

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is SpecificationsIntents.Back -> back()

            is SpecificationsIntents.BackFromDataEntry -> backFromDaraEntry()

            is SpecificationsIntents.BackFromAddProducts -> backFromAddProducts()

            is SpecificationsIntents.BackFromListProducts -> backFromListProducts()

            is SpecificationsIntents.OpenListProducts -> openListProducts(intent.list)

            is SpecificationsIntents.SelectProduct -> selectProduct(intent.item)

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

            is SpecificationsIntents.Next -> { next( intent.selectedCurrency,intent.selectedWarehouse,

                intent.selectedStatus ) }

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

    fun backFromAddProducts () {

        state = state.copy(

            isVisibilityDataEntry = true,

            isVisibilityAddProducts = false

        )

    }

    fun backFromListProducts () {

        state = state.copy(

            isVisibilityListProducts = false,

            isVisibilityAddProducts = true


        )

    }

    fun next ( selectedCurrency: CurrencyResponseModel?, selectedWarehouse: WarehouseModel?,

              selectedStatus: Int? ) {

        state = state.copy(

            selectedStatus = selectedStatus,

            selectedWarehouse = selectedWarehouse,

            selectedCurrency = selectedCurrency,

            isVisibilityDataEntry = false,

            isVisibilityAddProducts = true

        )

    }

    fun openListProducts (list: List<ElementSpecification> ) {

    state = state.copy(

        listElementsSpecifications = list,

        isVisibilityAddProducts = false,

        isVisibilityListProducts = true

    )

    }

    fun selectProduct ( item: ProductResponseModel ) {

        val newList = state.listElementsSpecifications.toMutableList()

        val lastElement = newList.last()
        val updatedLastElement = lastElement.copy(
            product = lastElement.product.toMutableList().apply { add(item) },
            count = lastElement.count.toMutableList().apply { add("") },
            totalPrice = lastElement.totalPrice.toMutableList().apply { add("") },
            spectext = lastElement.spectext.toMutableList().apply { add("") },
            price_item = lastElement.price_item.toMutableList().apply { add("") },
            nds = lastElement.nds.toMutableList().apply { add("") }
        )

        newList[newList.size - 1] = updatedLastElement

        state = state.copy(
            listElementsSpecifications = newList,
            isVisibilityListProducts = false,
            isVisibilityAddProducts = true,
        )

        println("LIST: ${ state.listElementsSpecifications }")

    }

}