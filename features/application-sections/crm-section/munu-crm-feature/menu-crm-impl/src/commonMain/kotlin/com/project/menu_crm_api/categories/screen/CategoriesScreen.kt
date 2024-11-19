package com.project.menu_crm_api.categories.screen

import com.project.core_app.network_base_screen.NetworkScreen
import com.project.menu_crm_api.categories.component.CategoriesComponent
import org.koin.mp.KoinPlatform.getKoin

class CategoriesScreen : NetworkScreen(

    CategoriesComponent(getKoin().get())

)