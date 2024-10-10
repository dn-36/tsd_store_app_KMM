package com.project

import cafe.adriel.voyager.core.screen.Screen
import com.project.menu.screen.OrganizationScreenApi

class OrganizationScreenImpl(): OrganizationScreenApi {
    override fun organization(): Screen {
      return OrganizationScreen()
    }

}