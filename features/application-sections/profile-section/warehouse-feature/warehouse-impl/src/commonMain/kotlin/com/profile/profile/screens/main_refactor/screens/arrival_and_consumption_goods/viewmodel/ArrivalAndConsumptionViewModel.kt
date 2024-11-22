package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.CreateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.DeleteArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.UpdateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel (

    val getContagentsUseCase: GetContagentsUseCase,

    val getProductsUseCase: GetProductsUseCase,

    val getWarehouseArrivalAndConsumptionUseCase: GetWarehouseArrivalAndConsumptionUseCase,

    val getArrivalAndConsumptionUseCase: GetArrivalAndConsumptionUseCase,

    val deleteArrivalOrConsumptionUseCase: DeleteArrivalOrConsumptionUseCase,

    val createArrivalOrConsumptionUseCase: CreateArrivalOrConsumptionUseCase,

    val updateArrivalOrConsumptionUseCase: UpdateArrivalOrConsumptionUseCase



) : NetworkViewModel() {

    var state by mutableStateOf(ArrivalAndConsumptionState())

    fun processIntent(intent: ArrivalAndConsumptionIntents) {

        when (intent) {

            is ArrivalAndConsumptionIntents.ArrivalOrConsumption -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    getContagentsUseCase.execute(onGet = { newListContragents ->

                        val newList = mutableListOf<ContragentResponseArrivalAndConsumption>()

                        newListContragents.forEach {

                            val newListEntity = mutableListOf<EntityArrivalAndConsumption>()

                            if (it.entits != null) {

                                it.entits.forEach { entity ->

                                    newListEntity.add(
                                        EntityArrivalAndConsumption(

                                            id = entity.id,

                                            name = entity.name,

                                            ui = entity.ui

                                        )
                                    )

                                }
                            }


                            newList.add(

                                ContragentResponseArrivalAndConsumption(

                                    id = it.id,

                                    name = it.name,

                                    ui = it.ui,

                                    own = it.own,

                                    entits = newListEntity

                                )
                            )

                        }

                        state = state.copy(

                            listAllContragent = newList,

                            )

                    })

                    getWarehouseArrivalAndConsumptionUseCase.execute(onGet = { listAllWarehouse ->

                        state = state.copy(

                            listAllWarehouse = listAllWarehouse,

                            isUpdate = false,

                            isVisibilityDataEntryComponent = mutableStateOf(1f),

                            isVisibilityDeleteComponent = mutableStateOf(0f),

                            isPush = intent.isPush

                        )

                    })

                    getProductsUseCase.execute(onGet = { listAllProducts ->

                        state = state.copy(

                            listProducts = listAllProducts

                        )

                    })

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    deleteArrivalOrConsumptionUseCase.execute(ui = state.updatedItem!!.ui!!)

                    state = state.copy(

                        isVisibilityDeleteComponent = mutableStateOf(0f)

                    )

                    processIntent(
                        ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods(intent.coroutineScope)
                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ArrivalAndConsumptionIntents.BackAddProducts -> backAddProducts()

            is ArrivalAndConsumptionIntents.Update -> {

                if (state.listSelectedProducts.size != 0) {

                    setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        updateArrivalOrConsumptionUseCase.execute(

                            productUi = state.updatedItem!!.ui!!,

                            idLegalEntityParish = state.idLegalEntityParish,

                            idLegalEntityExpense = state.idLegalEntityExpense,

                            idContragentExpense = state.idContragentExpense,

                            idContragentParish = state.idContragentParish,

                            idWarehouse = state.idWarehouse,

                            isPush = state.isPush,

                            listProducts = state.listSelectedProducts
                        )

                        state = state.copy(

                            isVisibilityAddProductsComponent = mutableStateOf(0f),

                            updatedEntityExpense = null,

                            updatedEntityParish = null,

                            updatedWarehouse = null,

                            updatedContragentParish = null,

                            updatedContragentExpense = null,

                            listSelectedProducts = emptyList()

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }

                }

            }

            is ArrivalAndConsumptionIntents.UpdateButton -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    getContagentsUseCase.execute(onGet = { newListContragents ->

                        val newList = mutableListOf<ContragentResponseArrivalAndConsumption>()

                        newListContragents.forEach {

                            val newListEntity = mutableListOf<EntityArrivalAndConsumption>()

                            if (it.entits != null) {

                                it.entits.forEach { entity ->

                                    newListEntity.add(
                                        EntityArrivalAndConsumption(

                                            id = entity.id,

                                            name = entity.name,

                                            ui = entity.ui

                                        )
                                    )

                                }
                            }


                            newList.add(

                                ContragentResponseArrivalAndConsumption(

                                    id = it.id,

                                    name = it.name,

                                    ui = it.ui,

                                    own = it.own,

                                    entits = newListEntity

                                )
                            )

                        }

                        state = state.copy(

                            listAllContragent = newList,

                            )

                    })

                    getWarehouseArrivalAndConsumptionUseCase.execute(onGet = { listAllWarehouse ->

                        state = state.copy(

                            listAllWarehouse = listAllWarehouse,

                            isPush = intent.item.is_push!!

                        )

                    })



                    getProductsUseCase.execute(onGet = { listAllProducts ->

                        val newListContragents =
                            mutableListOf<ContragentResponseArrivalAndConsumption>()

                        state.listAllContragent.forEach { item ->

                            if (item.entits != null) {

                                if (item.entits.isNotEmpty()) {

                                    newListContragents.add(

                                        ContragentResponseArrivalAndConsumption(

                                            id = item.id,

                                            name = item.name,

                                            ui = item.ui,

                                            own = item.own,

                                            entits = item.entits

                                        )
                                    )

                                }

                            }

                        }

                        val newListWarehouse = mutableListOf<WarehouseArrivalAndConsumption>()

                        state.listAllWarehouse.forEach { item ->

                            if (item.stores != null) {

                                if (item.stores.isNotEmpty()) {

                                    newListWarehouse.add(item)

                                }

                            }

                        }


                        val newSelectedProduct = mutableListOf<ProductArrivalAndConsumption>()

                        listAllProducts.forEach { item ->

                            intent.item.products!!.forEach {

                                if (it!!.product_id == item.id) {

                                    newSelectedProduct.add(
                                        ProductArrivalAndConsumption(
                                            product = item,
                                            count = it.count!!
                                        )
                                    )

                                }

                            }
                        }

                        val newContragentExpense =
                            newListContragents.find { it.id == intent.item.contragent_id }

                        val newContragentParish =
                            newListContragents.find { it.id == intent.item.contragent_push_id }

                        val newEntityExpense =
                            newListContragents.find { it.entits!![0].id == intent.item.entity_push_id }

                        val newEntityParish =
                            newListContragents.find { it.entits!![0].id == intent.item.entity_id }

                        val newWarehouse =
                            newListWarehouse.find { it.stores[0]!!.id == intent.item.store_id }


                        state = state.copy(

                            updatedEntityExpense = newEntityExpense,

                            updatedWarehouse = newWarehouse,

                            updatedEntityParish = newEntityParish,

                            updatedContragentExpense = newContragentExpense,

                            updatedContragentParish = newContragentParish,

                            listSelectedProducts = newSelectedProduct,

                            listProducts = listAllProducts,

                            updatedItem = intent.item,

                            isUpdate = true,

                            isVisibilityDataEntryComponent = mutableStateOf(1f)


                        )

                    })

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ArrivalAndConsumptionIntents.CreateArrivalOrConsumption -> {

                if (state.listSelectedProducts.size != 0) {

                    setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        createArrivalOrConsumptionUseCase.execute(

                            idLegalEntityParish = state.idLegalEntityParish,

                            idLegalEntityExpense = state.idLegalEntityExpense,

                            idContragentExpense = state.idContragentExpense,

                            idContragentParish = state.idContragentParish,

                            idWarehouse = state.idWarehouse,

                            isPush = state.isPush,

                            listProducts = state.listSelectedProducts
                        )

                        state = state.copy(

                            isVisibilityAddProductsComponent = mutableStateOf(0f),

                            listSelectedProducts = emptyList()

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }

                }

            }

            is ArrivalAndConsumptionIntents.BackListProducts -> backListProducts()

            is ArrivalAndConsumptionIntents.Ready -> ready(intent.count)

            is ArrivalAndConsumptionIntents.SelectProducts -> selectProduct(intent.selectedProducts)

            is ArrivalAndConsumptionIntents.Scanner -> scanner()

            is ArrivalAndConsumptionIntents.CanselScanner -> canselScanner()

            is ArrivalAndConsumptionIntents.SelectFromList -> selectFromList()

            is ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    getArrivalAndConsumptionUseCase.execute(onGet = { listArrivalAndConsumption ->

                        state = state.copy(

                            listAllArrivalOrConsumption = listArrivalAndConsumption

                        )

                    })

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ArrivalAndConsumptionIntents.BackDataEntry -> {

                backDataEntry()
            }

            is ArrivalAndConsumptionIntents.Next -> {

                next(
                    intent.idLegalEntityParish, intent.idLegalEntityExpense,

                    intent.idContragentExpense, intent.idContragentParish, intent.idWarehouse
                )

            }

            is ArrivalAndConsumptionIntents.CanselSelectedProduct -> {

                canselSelectedProduct(intent.index)
            }

            is ArrivalAndConsumptionIntents.AddProductScanner -> {
                addProductScanner(intent.name)
            }

            is ArrivalAndConsumptionIntents.NoDelete -> {
                noDelete()
            }

            is ArrivalAndConsumptionIntents.OpenDeleteComponent -> {

                openDeleteComponent(intent.item!!)
            }

        }
    }

    fun backAddProducts() {

        state = state.copy(

            isVisibilityAddProductsComponent = mutableStateOf(0f),

            isVisibilityDataEntryComponent = mutableStateOf(1f)

        )

    }

    fun backListProducts() {

        state = state.copy(

            isVisibilityListProducts = mutableStateOf(0f),

            isVisibilityAddProductsComponent = mutableStateOf(1f)

        )

    }

    fun selectProduct(selectedProduct: ProductArrivalAndConsumption) {

        state = state.copy(

            isVisibilityCountProducts = mutableStateOf(1f),

            isVisibilityListProducts = mutableStateOf(0f),

            selectedProduct = selectedProduct

        )

    }

    fun scanner() {

        state = state.copy(

            isVisibilityScannerComponent = mutableStateOf(1f),

            isVisibilityAddProductsComponent = mutableStateOf(0f)

        )

    }


    fun ready(count: String) {

        val cont = count.toDoubleOrNull()

        println("CHECK COUNT: ${cont}")

        if (count.isNotBlank() && count.toDoubleOrNull() != null && count.toDouble() > 0) {

            val newProduct = state.selectedProduct

            newProduct!!.count = count.toDouble()

            val newList = state.listSelectedProducts.toMutableList()

            newList.add(newProduct)

            state = state.copy(

                selectedProduct = newProduct,

                isVisibilityCountProducts = mutableStateOf(0f),

                isVisibilityAddProductsComponent = mutableStateOf(1f),

                listSelectedProducts = newList,

                colorBorderCountTF = Color.LightGray

            )

        } else {

            state = state.copy(

                colorBorderCountTF = Color.Red

            )

        }

    }

    fun backDataEntry() {

        state = state.copy(

            isVisibilityDataEntryComponent = mutableStateOf(0f),

            updatedEntityExpense = null,

            updatedEntityParish = null,

            updatedWarehouse = null,

            updatedContragentParish = null,

            updatedContragentExpense = null

        )

    }

    fun next(
        idLegalEntityParish: Int?, idLegalEntityExpense: Int?, idContragentExpense: Int?,

        idContragentParish: Int?, idWarehouse: Int?
    ) {

        if (idLegalEntityParish != null && idLegalEntityExpense != null &&

            idContragentExpense != null && idContragentParish != null && idWarehouse != null
        ) {

            state = state.copy(

                isVisibilityAddProductsComponent = mutableStateOf(1f),

                isVisibilityDataEntryComponent = mutableStateOf(0f),

                idContragentExpense = idContragentExpense,

                idContragentParish = idContragentParish,

                idLegalEntityExpense = idLegalEntityExpense,

                idLegalEntityParish = idLegalEntityParish,

                idWarehouse = idWarehouse,

                )

        }

    }

    fun canselSelectedProduct(index: Int) {

        val newList = state.listSelectedProducts.toMutableList()

        newList.removeAt(index)

        state = state.copy(

            listSelectedProducts = newList

        )

    }

    fun addProductScanner(sku: String) {
        //openWifiSettings()
        val selectedProduct = state.listProducts.find { it.sku == sku }

        println("AllProduct : ${state.listProducts}")

        println("SelectedProduct : ${selectedProduct}")

        if (selectedProduct != null) {

            state = state.copy(

                isVisibilityCountProducts = mutableStateOf(1f),

                isVisibilityScannerComponent = mutableStateOf(0f),

                selectedProduct = ProductArrivalAndConsumption(
                    product = selectedProduct,
                    count = 0.0
                )

            )
        }

    }

    fun selectFromList() {

        state = state.copy(

            isVisibilityListProducts = mutableStateOf(1f),

            isVisibilityAddProductsComponent = mutableStateOf(0f)

        )

    }

    fun canselScanner() {

        state = state.copy(

            isVisibilityScannerComponent = mutableStateOf(0f),

            isVisibilityAddProductsComponent = mutableStateOf(1f)

        )

    }

    fun noDelete() {

        state = state.copy(

            isVisibilityDeleteComponent = mutableStateOf(0f),

            updatedItem = null

        )

    }

    fun openDeleteComponent(item: StoreResponseArrivalAndConsumption) {

        state = state.copy(

            isVisibilityDeleteComponent = mutableStateOf(1f),

            updatedItem = item

        )

    }

}

