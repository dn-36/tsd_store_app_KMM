package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.AddProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ArrivalGoodsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.CountProductComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ListProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen.ArrivalAndConsumptionScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.contragent_network.ContragentClient
import com.project.network.warehouse_network.WarehouseClient
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel (

    val getContagentsUseCase: GetContagentsUseCase,

    val getProductsUseCase: GetProductsUseCase,

    val getWarehouseArrivalAndConsumptionUseCase: GetWarehouseArrivalAndConsumptionUseCase

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

                            )

                    })

                    Navigation.navigator.push(

                        ArrivalGoodsComponent(

                            listAllContragents = arrivalAndConsumptionState.listAllContragent,

                            listAllWarehouse = arrivalAndConsumptionState.listAllWarehouse,

                            onClickBack = { processIntent(ArrivalAndConsumptionIntents.Back) },

                            onClickNext = { processIntent(ArrivalAndConsumptionIntents.Next(intent.coroutineScope)) }

                        )
                    )

                }
                //arrival(intent.coroutineScope)
            }

            is ArrivalAndConsumptionIntents.Back -> {

                back()
            }

            is ArrivalAndConsumptionIntents.Next -> {

                next(intent.coroutineScope)
            }

            is ArrivalAndConsumptionIntents.ProductSelection -> {

                productSelection()
            }

            is ArrivalAndConsumptionIntents.SelectFromList -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getProductsUseCase.execute ( onGet = { listAllProducts ->

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listAllProducts = listAllProducts

                        )

                    })

                    Navigation.navigator.push(ListProductsComponent(arrivalAndConsumptionState.listAllProducts,
                       onClickProduct =  { processIntent(ArrivalAndConsumptionIntents.ProductSelection )}))

                    val newList = arrivalAndConsumptionState.selectedProducts.toMutableList()

                    newList.add(ProductArrivalAndConsumption(ListProductsComponent(arrivalAndConsumptionState.listAllProducts,
                        onClickProduct =  { processIntent(ArrivalAndConsumptionIntents.ProductSelection )}).selectedProduct!!.id!!,0))

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        selectedProducts = newList

                    )
                }
            }
        }
    }

    fun back(){

        Navigation.navigator.push(ArrivalAndConsumptionScreen())

    }

    fun ready(coroutineScope: CoroutineScope,count: String ){

        Navigation.navigator.push(AddProductsComponent(onClickSelectFromList =

        { processIntent(ArrivalAndConsumptionIntents.SelectFromList(coroutineScope)) }))

        var newList = arrivalAndConsumptionState.selectedProducts.toMutableList()

        newList[newList.size - 1] = ProductArrivalAndConsumption(id = arrivalAndConsumptionState.selectedProducts[0].id,count.toInt())

    }

    fun productSelection(){

        Navigation.navigator.push(CountProductComponent(arrivalAndConsumptionState.listAllProducts))

    }

    fun next(coroutineScope: CoroutineScope){

        Navigation.navigator.push( AddProductsComponent( onClickSelectFromList =

        { processIntent(ArrivalAndConsumptionIntents.SelectFromList(coroutineScope)) }))

    }

}