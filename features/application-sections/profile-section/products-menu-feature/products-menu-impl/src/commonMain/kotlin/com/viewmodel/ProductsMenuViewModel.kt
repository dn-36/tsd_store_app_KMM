package com.viewmodel

import androidx.lifecycle.ViewModel
import com.CategoriesScreenApi
import com.project.network.Navigation
import org.koin.mp.KoinPlatform

class ProductsMenuViewModel: ViewModel() {

    fun processIntents ( intent: ProductsMenuIntents ) {

        when ( intent ) {

            is ProductsMenuIntents.Categories -> categories()

        }

    }

    fun categories() {

        val categoriesScreen: CategoriesScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(categoriesScreen.categoriesScreen())

    }

}