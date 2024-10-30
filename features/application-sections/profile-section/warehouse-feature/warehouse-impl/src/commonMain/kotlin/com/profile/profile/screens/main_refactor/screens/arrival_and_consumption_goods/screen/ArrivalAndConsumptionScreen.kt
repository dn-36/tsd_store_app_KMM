package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen


import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ArrivalAndConsumptionComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin

class ArrivalAndConsumptionScreen : NetworkScreen(

    ArrivalAndConsumptionComponent ( getKoin().get() )

)