package com.screen

import com.component.ProductCategoriesComponent
import com.project.core_app.network_base_screen.NetworkScreen
import org.koin.mp.KoinPlatform.getKoin

class ProductCategoriesScreen: NetworkScreen (

    ProductCategoriesComponent(getKoin().get())

)