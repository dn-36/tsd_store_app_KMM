package com.profile.`printer-impl`

import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.ProfileScreensApi
import com.profile.profile.screens.profile_screen.screen.ProfileScreen
import com.profile.profile.screens.warehouse_feature.main_refactor.screen.WarehouseScreen

class ProfileScreensImpl():ProfileScreensApi {
    override fun profile(): Screen {
       return ProfileScreen
    }

    override fun warehouse(): Screen {
     return WarehouseScreen
    }
}