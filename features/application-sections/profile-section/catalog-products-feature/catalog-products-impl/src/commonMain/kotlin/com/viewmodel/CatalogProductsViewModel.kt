package com.viewmodel

import androidx.lifecycle.ViewModel
import com.ProductCategoriesScreenApi
import com.project.network.Navigation
import org.koin.mp.KoinPlatform

class CatalogProductsViewModel: ViewModel() {

    fun processIntents( intent: CatalogProductsIntents ) {

        when ( intent ) {

        is CatalogProductsIntents.Back -> back()

        }

    }

    fun back(){

        val productCategories: ProductCategoriesScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(productCategories.productCategories())

    }

}