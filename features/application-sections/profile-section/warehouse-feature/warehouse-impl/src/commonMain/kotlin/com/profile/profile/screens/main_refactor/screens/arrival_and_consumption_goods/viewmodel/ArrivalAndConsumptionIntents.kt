package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ArrivalAndConsumptionIntents {
data class Arrival ( val coroutineScope: CoroutineScope ) : ArrivalAndConsumptionIntents()
    object Back : ArrivalAndConsumptionIntents()
    data class Next (val coroutineScope: CoroutineScope ) : ArrivalAndConsumptionIntents()
    data class SelectFromList (val coroutineScope: CoroutineScope ) : ArrivalAndConsumptionIntents()
    object ProductSelection : ArrivalAndConsumptionIntents()

}