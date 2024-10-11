package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ArrivalGoods
import com.project.network.Navigation

class ArrivalAndConsumptionViewModel:ViewModel() {

    var arrivalAndConsumptionState by mutableStateOf(ArrivalAndConsumptionState())

    fun processIntent(intent: ArrivalAndConsumptionIntents){
        when(intent){
            is ArrivalAndConsumptionIntents.Arrival -> {arrival(intent.vm)}
        }
    }

    fun arrival(vm: ArrivalAndConsumptionViewModel){

        Navigation.navigator.push(ArrivalGoods(vm))

    }
}