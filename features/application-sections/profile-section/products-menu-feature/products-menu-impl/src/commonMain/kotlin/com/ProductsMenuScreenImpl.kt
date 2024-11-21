package com

import cafe.adriel.voyager.core.screen.Screen
import com.screen.ProductsMenuScreen

class ProductsMenuScreenImpl : ProductsMenuScreenApi {

    override fun productsMenuScreen(): Screen {

        return ProductsMenuScreen()

    }

}