package com

import cafe.adriel.voyager.core.screen.Screen
import com.screen.ProductCategoriesScreen

class ProductCategoriesScreenImpl: ProductCategoriesScreenApi {

    override fun productCategories(): Screen {

        return ProductCategoriesScreen()

    }

}