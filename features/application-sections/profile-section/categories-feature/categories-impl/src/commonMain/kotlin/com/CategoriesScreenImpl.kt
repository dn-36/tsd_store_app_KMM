package com

import cafe.adriel.voyager.core.screen.Screen
import com.screen.CategoriesScreen

class CategoriesScreenImpl: CategoriesScreenApi {

    override fun categoriesScreen(): Screen {

        return CategoriesScreen()

    }

}