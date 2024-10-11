package com.profile.profile

import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.warehouse.screen.WarehouseScreen
import com.project.chats.WarehouseScreensApi

class WarehouseScreensImpl():WarehouseScreensApi {
    override fun warehouse(): Screen {
       return WarehouseScreen()
    }

}