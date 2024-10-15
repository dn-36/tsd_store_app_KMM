package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.AddProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ArrivalGoodsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ListProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen.ArrivalAndConsumptionScreen
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.contragent_network.ContragentApi
import com.project.network.warehouse_network.WarehouseApi
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel : ViewModel() {

    var arrivalAndConsumptionState by mutableStateOf(ArrivalAndConsumptionState())

    fun processIntent(intent: ArrivalAndConsumptionIntents) {
        when (intent) {

            is ArrivalAndConsumptionIntents.Arrival -> {
                arrival(intent.coroutineScope)
            }

            is ArrivalAndConsumptionIntents.Back -> {
                back()
            }

            is ArrivalAndConsumptionIntents.Next -> {
                next()
            }

            is ArrivalAndConsumptionIntents.SelectFromList -> {
                selectFromList()
            }
        }
    }

    fun arrival(coroutineScope: CoroutineScope) {

        val token = ConstData.TOKEN

        ContragentApi.token = token

        WarehouseApi.token = token

        coroutineScope.launch(Dispatchers.IO) {

            val newListContragents = ContragentApi.getContragents()

            println("контраегнты: ${newListContragents}")

            val newListWarehouse = mutableListOf<Warehouse>()

            WarehouseApi.getWarehouse().forEach {

                if (it.stores.isNotEmpty()) {

                    newListWarehouse.add(it)

                }

            }

            arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                listAllContragent = newListContragents,

                listAllWarehouse = newListWarehouse

            )

            Navigation.navigator.push(

                ArrivalGoodsComponent(

                    listAllContragents = arrivalAndConsumptionState.listAllContragent,

                    listAllWarehouse = arrivalAndConsumptionState.listAllWarehouse,

                    onClickBack = { processIntent(ArrivalAndConsumptionIntents.Back) },

                    onClickNext = { processIntent(ArrivalAndConsumptionIntents.Next) }

                )
            )

        }
    }

    fun back(){

        Navigation.navigator.push(ArrivalAndConsumptionScreen())

    }

    fun next(){

        Navigation.navigator.push( AddProductsComponent( onClickSelectFromList =

        { processIntent(ArrivalAndConsumptionIntents.SelectFromList) }))

    }

    fun selectFromList(){

        Navigation.navigator.push(ListProductsComponent())

    }

}