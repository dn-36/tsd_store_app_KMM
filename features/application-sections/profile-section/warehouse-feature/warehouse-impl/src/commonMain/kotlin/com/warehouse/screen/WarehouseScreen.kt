package com.profile.profile.screens.main_refactor.screens.warehouse.screen


import com.profile.profile.screens.main_refactor.screens.warehouse.component.WarehouseComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin

class WarehouseScreen : NetworkScreen(

    WarehouseComponent ( getKoin().get() )

)