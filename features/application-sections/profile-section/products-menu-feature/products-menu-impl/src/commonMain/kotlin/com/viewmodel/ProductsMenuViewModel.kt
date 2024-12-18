package com.viewmodel

import UnitsMeasurementScreenApi
import androidx.lifecycle.ViewModel
import com.CategoriesScreenApi
import com.project.network.Navigation
import org.koin.mp.KoinPlatform

class ProductsMenuViewModel: ViewModel() {

    fun processIntents ( intent: ProductsMenuIntents ) {

        when ( intent ) {

            is ProductsMenuIntents.Categories -> categories()

            is ProductsMenuIntents.UnitsMeasurement -> unitsMeasurement()

        }

    }

    fun categories() {

        val categoriesScreen: CategoriesScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(categoriesScreen.categoriesScreen())

    }

    fun unitsMeasurement() {

        val unitsMeasurementScreen: UnitsMeasurementScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(unitsMeasurementScreen.unitMeasurement())

    }

}