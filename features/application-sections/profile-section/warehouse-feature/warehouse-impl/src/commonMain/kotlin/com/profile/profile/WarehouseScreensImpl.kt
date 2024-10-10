package com.profile.profile

import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.warehouse_feature.main_refactor.screen.WarehouseScreen
import com.project.chats.WarehouseScreensApi

class WarehouseScreensImpl():WarehouseScreensApi {
    override fun warehouse(): Screen {
       return WarehouseScreen()
    }

}