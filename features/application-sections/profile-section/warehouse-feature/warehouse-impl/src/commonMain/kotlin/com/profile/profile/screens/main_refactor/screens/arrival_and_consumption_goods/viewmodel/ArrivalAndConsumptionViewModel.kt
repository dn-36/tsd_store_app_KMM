package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.CreateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.DeleteArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.project.network.arrival_goods.model.StoreResponse
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel (

    val getContagentsUseCase: GetContagentsUseCase,

    val getProductsUseCase: GetProductsUseCase,

    val getWarehouseArrivalAndConsumptionUseCase: GetWarehouseArrivalAndConsumptionUseCase,

    val getArrivalAndConsumptionUseCase: GetArrivalAndConsumptionUseCase,

    val deleteArrivalOrConsumptionUseCase: DeleteArrivalOrConsumptionUseCase,

    val createArrivalOrConsumptionUseCase: CreateArrivalOrConsumptionUseCase

) : ViewModel() {

    var arrivalAndConsumptionState by mutableStateOf(ArrivalAndConsumptionState())

    fun processIntent(intent: ArrivalAndConsumptionIntents) {
        when (intent) {

            is ArrivalAndConsumptionIntents.Arrival -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getContagentsUseCase.execute ( onGet = { newListContragents ->

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listAllContragent = newListContragents,

                        )

                    })

                    getWarehouseArrivalAndConsumptionUseCase.execute ( onGet = { listAllWarehouse ->

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listAllWarehouse = listAllWarehouse,

                            isVisibilityDataEntryComponent = mutableStateOf(1f),

                            isPush = 1

                            )

                    })

                }

            }

            is ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteArrivalOrConsumptionUseCase.execute( ui = intent.ui )

                    processIntent(ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods(intent.coroutineScope))

                }

            }

            is ArrivalAndConsumptionIntents.BackAddProducts ->  backAddProducts()

            is ArrivalAndConsumptionIntents.Update -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                getContagentsUseCase.execute ( onGet = { newListContragents ->

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        listAllContragent = newListContragents,

                        )

                })

                getWarehouseArrivalAndConsumptionUseCase.execute ( onGet = { listAllWarehouse ->

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        listAllWarehouse = listAllWarehouse,

                       // isVisibilityDataEntryComponent = mutableStateOf(1f),

                        isPush = 1

                    )

                })



                    getProductsUseCase.execute ( onGet = { listAllProducts ->

                        val newListContragents = mutableListOf<ContragentResponse>()

                        arrivalAndConsumptionState.listAllContragent.forEach { item ->

                            if(item.entits != null ){

                                if(item.entits!!.isNotEmpty()){

                                    newListContragents.add(item)

                                }

                            }

                        }

                        val newListWarehouse = mutableListOf<Warehouse>()

                        arrivalAndConsumptionState.listAllWarehouse.forEach { item ->

                            if(item.stores != null ){

                                if(item.stores.isNotEmpty()){

                                    newListWarehouse.add(item)

                                }

                            }

                        }


                        val newSelectedProduct = mutableListOf<ProductArrivalAndConsumption>()

                        listAllProducts.forEach { item ->

                            intent.item.products!!.forEach {

                                if (it!!.id == item.id){

                                    newSelectedProduct.add(ProductArrivalAndConsumption(product = item, count = it.count!!))

                                }

                            }
                        }

                        val newContragentExpense =  newListContragents.find { it.id == intent.item.contragent_id }

                        val newContragentParish =  newListContragents.find { it.id == intent.item.contragent_push_id }

                        val newContragentEntityExpense =  newListContragents.find { it.entits!![0].id == intent.item.entity_id }

                        val newContragentEntityParish =  newListContragents.find { it.entits!![0].id == intent.item.entity_push_id }

                        val newWarehouse =  newListWarehouse.find { it.stores[0]!!.id == intent.item.store_id}


                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            isVisibilityAddProductsUpdate = mutableStateOf(1f),

                            newContragentEntityExpense = newContragentEntityExpense,

                            newWarehouse = newWarehouse,

                            newContragentEntityParish = newContragentEntityParish,

                            newContragentExpense = newContragentExpense,

                            newContragentParish = newContragentParish,

                            listSelectedProducts = newSelectedProduct,

                            listProducts = listAllProducts


                        )

                    })
            }

                println("14")
                println("14")
                println("14")
                println("ItemProducts${intent.item.products}")

            }

            is ArrivalAndConsumptionIntents.CreateArrivalOrConsumption ->  {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    createArrivalOrConsumptionUseCase.execute (idLegalEntityParish = arrivalAndConsumptionState.idLegalEntityParish,
                        idLegalEntityExpense = arrivalAndConsumptionState.idLegalEntityExpense,
                        idContragentExpense = arrivalAndConsumptionState.idContragentExpense,
                        idContragentParish = arrivalAndConsumptionState.idContragentParish,
                        idWarehouse = arrivalAndConsumptionState.idWarehouse,
                        isPush = arrivalAndConsumptionState.isPush,
                        listProducts = arrivalAndConsumptionState.listSelectedProducts)

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(
                        isVisibilityAddProductsComponent = mutableStateOf(0f)
                    )

                }

            }

            is ArrivalAndConsumptionIntents.BackListProducts ->  backListProducts()

            is ArrivalAndConsumptionIntents.Ready ->  ready( intent.count )

            is ArrivalAndConsumptionIntents.SelectProducts ->  selectProduct( intent.selectedProducts )

            is ArrivalAndConsumptionIntents.Scanner ->  scanner()

            is ArrivalAndConsumptionIntents.SelectFromList -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getProductsUseCase.execute ( onGet = { listAllProducts ->

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listProducts = listAllProducts,

                            isVisibilityListProducts = mutableStateOf(1f),

                        )

                    })

                }

            }

            is ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods -> {

            intent.coroutineScope.launch (Dispatchers.IO) {

                getArrivalAndConsumptionUseCase.execute ( onGet = { listArrivalAndConsumption ->

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        listAllArrivalOrConsumption = listArrivalAndConsumption

                    )

                } )

            }

            }

            is ArrivalAndConsumptionIntents.BackDataEntry -> {

                backDataEntry()
            }

            is ArrivalAndConsumptionIntents.Next -> {

                next( intent.idLegalEntityParish, intent.idLegalEntityExpense, intent.idContragentExpense,

                    intent.idContragentParish, intent.idWarehouse )

            }

        }
    }

    fun backAddProducts(){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityAddProductsComponent = mutableStateOf(0f),

            isVisibilityDataEntryComponent = mutableStateOf(1f),

        )

    }

    fun backListProducts(){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityListProducts = mutableStateOf(0f),

            isVisibilityAddProductsComponent = mutableStateOf(1f)

        )

    }

    fun selectProduct(selectedProduct: ProductArrivalAndConsumption){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityCountProducts = mutableStateOf(1f),

            isVisibilityListProducts = mutableStateOf(0f),

            selectedProduct = selectedProduct

        )

    }

    fun scanner() {

        // Navigation.navigator.push()

    }


    fun ready ( count: Int ) {

        val newProduct = arrivalAndConsumptionState.selectedProduct

        newProduct!!.count = count

        val newList = arrivalAndConsumptionState.listSelectedProducts.toMutableList()

        newList.add(newProduct)

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            selectedProduct = newProduct,

            isVisibilityCountProducts = mutableStateOf(0f),

            listSelectedProducts = newList

        )

    }

    fun backDataEntry(){

    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

        isVisibilityDataEntryComponent = mutableStateOf(0f)

    )

    }

    fun next( idLegalEntityParish: Int?, idLegalEntityExpense: Int?, idContragentExpense: Int? ,

    idContragentParish: Int?, idWarehouse: Int? ){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityAddProductsComponent = mutableStateOf(1f),

            isVisibilityDataEntryComponent = mutableStateOf(0f),

            idContragentExpense = idContragentExpense,

            idContragentParish = idContragentParish,

            idLegalEntityExpense = idLegalEntityExpense,

            idLegalEntityParish = idLegalEntityParish,

            idWarehouse = idWarehouse

        )

    }

}