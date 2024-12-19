package com

import cafe.adriel.voyager.core.screen.Screen
import com.screen.CatalogProductsScreen

class CatalogProductsScreenImpl: CatalogProductsScreenApi {

    override fun catalogProductsScreen(): Screen {

       return CatalogProductsScreen()

    }

}