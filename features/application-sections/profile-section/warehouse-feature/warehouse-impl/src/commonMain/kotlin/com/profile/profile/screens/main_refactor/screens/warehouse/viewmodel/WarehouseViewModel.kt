package com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.warehouse.component.CreateWarehouseComponent
import com.project.core_app.ConstData
import com.project.network.Navigation
import com.project.network.organizations_network.OrganizationsApi
import com.project.network.warehouse_network.WarehouseApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class WarehouseViewModel:ViewModel() {

    var warehouseState by mutableStateOf(WarehouseState())

    fun processIntents(intent: WarehouseIntents){
        when(intent){
            is WarehouseIntents.AddWarehouse -> {addWarehouse(intent.coroutineScope)}
        }
    }

fun addWarehouse(coroutineScope: CoroutineScope){

    coroutineScope.launch(Dispatchers.IO) {

         val token = ConstData.TOKEN

         WarehouseApi.token = token

         val newList = WarehouseApi.getLocations()

        warehouseState = warehouseState.copy(
            listAllLocations = newList
        )
        println("Locations:${newList}")

        Navigation.navigator.push(CreateWarehouseComponent(warehouseState.listAllLocations))


    }

}

}