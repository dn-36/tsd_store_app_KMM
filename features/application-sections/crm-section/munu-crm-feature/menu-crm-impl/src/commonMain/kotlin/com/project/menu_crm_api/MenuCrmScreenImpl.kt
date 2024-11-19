package com.project.`menu-crm-impl`

import cafe.adriel.voyager.core.screen.Screen
import com.project.menu_crm_api.MenuCrmScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi

class MenuCrmScreenImpl: MenuCrmScreenApi {
    override fun MenuCrm(): Screen {
       return MenuCrmScreen()
    }

}