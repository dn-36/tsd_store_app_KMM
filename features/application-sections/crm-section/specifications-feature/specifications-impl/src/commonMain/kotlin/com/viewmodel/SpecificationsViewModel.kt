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
import com.domain.usecases.UpdateSpecificationUseCase
import com.model.CategoryProductModel
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

    val deleteSpecificationUseCase: DeleteSpecificationUseCase,

    val updateSpecificationUseCase: UpdateSpecificationUseCase

  ): NetworkViewModel() {

    var state by mutableStateOf(SpecificationsState())

    fun processIntents ( intent: SpecificationsIntents ) {

        when (intent) {

            is SpecificationsIntents.SetScreen -> {

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    if ( state.isSet )

                    state = state.copy(

                        listSpecifications = getSpecificationsUseCase.execute(),

                        listFilteredSpecifications = getSpecificationsUseCase.execute(),

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

                intent.list, intent.indexMainGroup, intent.byCategory
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

            is SpecificationsIntents.OpenUpdateDataEntry -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    state = state.copy(

                        listCurrency = getCurrencyUseCase.execute(),

                        listWarehouse = getWarehouseUseCase.execute(),

                        listProducts = getProductUseCase.execute(),

                        updateItem = intent.item,

                        listElementsSpecifications = intent.item.spec_item?.map {

                            val newListProduct = mutableListOf<ProductResponseModel>()

                            val newListCount= mutableListOf<String>()

                            val newListPriceItem= mutableListOf<String>()

                            val newListNDS = mutableListOf<String>()

                            val newListSpecText = mutableListOf<String>()

                            val newListTotalPrice = mutableListOf<String>()

                            newListProduct.add( ProductResponseModel(

                                id =  it.product?.id,
                                sku = it.product?.sku,
                                name = it.product?.name,
                                ui = it.product?.ui,
                                price = it.product?.price?.toFloat(),
                                category = CategoryProductModel(

                                    id = it.product?.category?.id,
                                    name = it.product?.category?.name,
                                    creater_id = it.product?.category?.creater_id,
                                    company_id = it.product?.category?.company_id

                                )

                            ))

                            newListCount.add(it.count.toString())

                            newListPriceItem.add(it.price?.toFloat().toString())

                            newListNDS.add(it.nds.toString())

                            newListSpecText.add(it.text?:"")

                            newListTotalPrice.add(

                                ((it.count ?: 0f) * (it.price?.toFloat() ?: 0f)).toString()

                            )

                        ElementSpecification (

                            product = newListProduct,
                            count = newListCount,
                            block  = it.block?:"",
                            price_item = newListPriceItem,
                            totalPrice = newListTotalPrice,
                            nds = newListNDS,
                            spectext = newListSpecText

                        )

                        }?: emptyList() ,

                        isVisibilityDataEntry = true

                    )

                    val mergedList = state.listElementsSpecifications
                        .groupBy { it.block } // Группируем элементы по значению block
                        .map { (block, elements) ->
                            // Берём первый элемент как базовый
                            val firstElement = elements.first()

                            // Объединяем данные остальных элементов с базовым
                            elements.drop(1).forEach { otherElement ->
                                firstElement.product.addAll(otherElement.product)
                                firstElement.count.addAll(otherElement.count)
                                firstElement.price_item.addAll(otherElement.price_item)
                                firstElement.totalPrice.addAll(otherElement.totalPrice)
                                firstElement.nds.addAll(otherElement.nds)
                                firstElement.spectext.addAll(otherElement.spectext)
                            }

                            // Возвращаем объединённый элемент
                            firstElement
                        }

                    state = state.copy(

                        listElementsSpecifications = mergedList

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

                        price = 1, status = state.selectedStatus?.second?:1, items = intent.list
                    )

                    state = state.copy(

                        listSpecifications = getSpecificationsUseCase.execute(),

                        listFilteredSpecifications = getSpecificationsUseCase.execute(),

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

            is SpecificationsIntents.UpdateSpecification -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    updateSpecificationUseCase.execute(

                        ui = state.updateItem!!.ui,

                        text = state.name,

                        valuta_id = state.selectedCurrency?.id,

                        local_store_id = if (state.selectedWarehouse != null)

                            state.selectedWarehouse!!.stores[0]!!.id ?: 0 else null,

                        price = 1, status = state.selectedStatus?.second?:1, items = intent.list
                    )

                    state = state.copy(

                        listSpecifications = getSpecificationsUseCase.execute(),

                        listFilteredSpecifications = getSpecificationsUseCase.execute(),

                        selectedCurrency = null,

                        selectedStatus = null,

                        selectedWarehouse = null,

                        listElementsSpecifications = emptyList(),

                        name = "",

                        updateItem = null,

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

                        listFilteredSpecifications = getSpecificationsUseCase.execute(),

                        updateItem = null,

                        isVisibilityDeleteComponent = false

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is SpecificationsIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is SpecificationsIntents.NoDelete -> noDelete()

            is SpecificationsIntents.InputTextSearchComponent -> {

                inputTextSearchComponent(intent.text)
            }

            is SpecificationsIntents.LongPressItem -> longPressItem(intent.index)

            is SpecificationsIntents.OnePressItem -> onePressItem()

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

            updateItem = null,

            name = "",

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

        selectedStatus: Pair<String,Int>?
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

        list: List<ElementSpecification>, index: Int?, byCategory: Float ) {

        state = state.copy(

            listElementsSpecifications = list,

            indexMainGroup = index,

            byCategory = byCategory,

            isVisibilityAddProducts = false,

            isVisibilityListProducts = true

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

            isVisibilityDeleteComponent = false,

            updateItem = null

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listSpecifications.filter {

            val companyName = it.text.orEmpty()

            companyName.contains(text, ignoreCase = true)

        }

        state = state.copy(

            listFilteredSpecifications = newList

        )

        println("Text ${text}")
        println("NewList ${newList}")

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listSpecifications.size){0F}

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listSpecifications.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

}