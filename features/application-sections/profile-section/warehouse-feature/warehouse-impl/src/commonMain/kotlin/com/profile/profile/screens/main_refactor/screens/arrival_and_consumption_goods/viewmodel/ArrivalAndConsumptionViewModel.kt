package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.CreateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.project.network.arrival_goods.ArrivalGoodsClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel (

    val getContagentsUseCase: GetContagentsUseCase,

    val getProductsUseCase: GetProductsUseCase,

    val getWarehouseArrivalAndConsumptionUseCase: GetWarehouseArrivalAndConsumptionUseCase,

    val getArrivalAndConsumptionUseCase: GetArrivalAndConsumptionUseCase,

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

            is ArrivalAndConsumptionIntents.BackAddProducts ->  backAddProducts()

            is ArrivalAndConsumptionIntents.CreateArrivalOrConsumption ->  {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    createArrivalOrConsumptionUseCase.execute (idLegalEntityParish = arrivalAndConsumptionState.idLegalEntityParish,
                        idLegalEntityExpense = arrivalAndConsumptionState.idLegalEntityExpense,
                        idContragentExpense = arrivalAndConsumptionState.idContragentExpense,
                        idContragentParish = arrivalAndConsumptionState.idContragentParish,
                        idWarehouse = arrivalAndConsumptionState.idWarehouse,
                        isPush = arrivalAndConsumptionState.isPush,
                        listProducts = arrivalAndConsumptionState.listSelectedProducts)

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

                    println("11111....${listArrivalAndConsumption}....1111")

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