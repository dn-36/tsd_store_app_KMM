package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

sealed class ArrivalAndConsumptionIntents {

data class Arrival (val vm : ArrivalAndConsumptionViewModel) :ArrivalAndConsumptionIntents()

}