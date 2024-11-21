package com.screen

import com.project.core_app.network_base_screen.NetworkScreen
import com.component.CategoriesComponent
import org.koin.mp.KoinPlatform

class CategoriesScreen : NetworkScreen(

    CategoriesComponent(KoinPlatform.getKoin().get())

)