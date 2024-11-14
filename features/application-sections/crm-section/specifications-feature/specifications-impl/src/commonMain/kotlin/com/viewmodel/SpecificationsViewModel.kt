package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.domain.usecases.CreateSpecificationUseCase
import com.domain.usecases.DeleteSpecificationUseCase
import com.domain.usecases.GetContragentsUseCase
import com.domain.usecases.GetCurrencyUseCase
import com.domain.usecases.GetProductUseCase
import com.domain.usecases.GetSpecificationsUseCase
import com.domain.usecases.GetWarehouseUseCase
import com.model.CurrencyResponseModel
import com.model.ElementSpecification
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
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

    val getProductUseCase: GetProductUseCase,

    val createSpecificationUseCase: CreateSpecificationUseCase,

    val deleteSpecificationUseCase: DeleteSpecificationUseCase

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

            is SpecificationsIntents.BackFromAddProducts -> backFromAddProducts()

            is SpecificationsIntents.BackFromListProducts -> backFromListProducts()

            is SpecificationsIntents.OpenListProducts -> openListProducts(
                intent.list,

                intent.indexMainGroup, intent.byCategory, intent.totalAmount
            )

            is SpecificationsIntents.SelectProduct -> selectProduct(intent.item)

            is SpecificationsIntents.OpenCreateDataEntry -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    state = state.copy(

                        listCurrency = getCurrencyUseCase.execute(),

                        listWarehouse = getWarehouseUseCase.execute(),

                        listProducts = getProductUseCase.execute(),

                        isVisibilityDataEntry = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is SpecificationsIntents.CreateSpecification -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    createSpecificationUseCase.execute(

                        text = state.name,

                        valuta_id = state.selectedCurrency?.id,

                        local_store_id = if (state.selectedWarehouse != null)

                            state.selectedWarehouse!!.stores[0]!!.id ?: 0 else null,

                        price = 1, status = 1, items = intent.list
                    )

                    state = state.copy(

                        listSpecifications = getSpecificationsUseCase.execute(),

                        selectedCurrency = null,

                        selectedStatus = null,

                        selectedWarehouse = null,

                        listElementsSpecifications = emptyList(),

                        name = "",

                        isVisibilityAddProducts = false

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is SpecificationsIntents.Next -> {

                next(

                    intent.name, intent.selectedCurrency,

                    intent.selectedWarehouse, intent.selectedStatus
                )
            }

            is SpecificationsIntents.DeleteSpecification -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    deleteSpecificationUseCase.execute(state.updateItem!!.ui)

                    state = state.copy(

                        listSpecifications = getSpecificationsUseCase.execute(),

                        updateItem = null,

                        isVisibilityDeleteComponent = false

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is SpecificationsIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is SpecificationsIntents.NoDelete -> noDelete()

        }

    }

    fun back() {

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(menuScreen.MenuCrm())

        println("SPECIFICATIONS: ${state.listSpecifications}")
        println("CONTRAGENTS: ${state.listContragents}")

    }

    fun backFromDaraEntry() {

        state = state.copy(

            isVisibilityDataEntry = false,

            selectedCurrency = null,

            selectedStatus = null,

            selectedWarehouse = null,

            listElementsSpecifications = emptyList()

        )

    }

    fun backFromAddProducts() {

        state = state.copy(

            isVisibilityDataEntry = true,

            isVisibilityAddProducts = false,

        )

    }

    fun backFromListProducts() {

        state = state.copy(

            isVisibilityListProducts = false,

            isVisibilityAddProducts = true


        )

    }

    fun next(

        name: String, selectedCurrency: CurrencyResponseModel?, selectedWarehouse: WarehouseModel?,

        selectedStatus: Int?
    ) {

        state = state.copy(

            selectedStatus = selectedStatus,

            selectedWarehouse = selectedWarehouse,

            selectedCurrency = selectedCurrency,

            name = name,

            isVisibilityDataEntry = false,

            isVisibilityAddProducts = true

        )

    }

    fun openListProducts(
        list: List<ElementSpecification>, index: Int?, byCategory: Float,

        totalAmount: String
    ) {

        state = state.copy(

            listElementsSpecifications = list,

            indexMainGroup = index,

            byCategory = byCategory,

            isVisibilityAddProducts = false,

            isVisibilityListProducts = true,

            totalAmount = totalAmount

        )

    }

    fun selectProduct(item: ProductResponseModel) {

        val newList = state.listElementsSpecifications.toMutableList()

        var index = 0

        println("BY CATEGORY: ${state.byCategory}")

        println("INDEXMAINGROUP: ${state.indexMainGroup}")

        if (state.listElementsSpecifications.size != 0) {

            if (state.byCategory == 1f) {

                val coincidence = newList.indexOfFirst {

                    it.block == item.category?.name ?: ""

                }

                /*
                indexOfFirst:
        •	Возвращает индекс первого элемента, который удовлетворяет заданному условию.
        •	Если ни один элемент не удовлетворяет условию, возвращается -1.
            */

                if (coincidence != -1) {

                    val element = newList[coincidence]

                    val updatedElement = element.copy(
                        product = element.product.toMutableList().apply { add(item) },
                        count = element.count.toMutableList().apply { add("1") },
                        totalPrice = element.totalPrice.toMutableList().apply { add("") },
                        spectext = element.spectext.toMutableList().apply { add("") },
                        price_item = element.price_item.toMutableList().apply { add("") },
                        nds = element.nds.toMutableList().apply { add("") }
                    )

                    newList[coincidence] = updatedElement

                    index = coincidence

                } else {

                    newList.add(

                        ElementSpecification(

                            product = mutableListOf(item),
                            count = mutableListOf("1"),
                            block = item.category?.name ?: "Нет названия группы",
                            price_item = mutableListOf(""),
                            nds = mutableListOf(""),
                            spectext = mutableListOf(""),
                            totalPrice = mutableListOf("")

                        )
                    )

                    index = newList.size - 1

                }
            } else {

                val element = newList[state.indexMainGroup ?: 0]

                val updatedElement = element.copy(
                    product = element.product.toMutableList().apply { add(item) },
                    count = element.count.toMutableList().apply { add("1") },
                    totalPrice = element.totalPrice.toMutableList().apply { add("") },
                    spectext = element.spectext.toMutableList().apply { add("") },
                    price_item = element.price_item.toMutableList().apply { add("") },
                    nds = element.nds.toMutableList().apply { add("") }
                )

                newList[state.indexMainGroup ?: 0] = updatedElement

                index = state.indexMainGroup ?: 0

            }

        } else {

            newList.add(
                ElementSpecification(

                    product = mutableListOf(item),
                    count = mutableListOf("1"),
                    block = item.category?.name ?: "Нет названия группы",
                    price_item = mutableListOf(""),
                    nds = mutableListOf(""),
                    spectext = mutableListOf(""),
                    totalPrice = mutableListOf("")

                )
            )

            index = 0

        }

        state = state.copy(
            listElementsSpecifications = newList,
            isVisibilityListProducts = false,
            isVisibilityAddProducts = true,
            indexMainGroup = index
        )

        println("STATE: ${state.listElementsSpecifications}")
    }

    fun openDeleteComponent ( item: SpecificResponseModel ) {

        state = state.copy(

            updateItem = item,

            isVisibilityDeleteComponent = true

        )

    }

    fun noDelete () {

        state = state.copy(

            isVisibilityDeleteComponent = false

        )

    }

}