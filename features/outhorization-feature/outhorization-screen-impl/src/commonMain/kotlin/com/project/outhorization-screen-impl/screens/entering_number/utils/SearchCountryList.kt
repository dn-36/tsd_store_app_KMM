package org.example.project.presentation.feature.authorization.screens.entering_number.utils

import com.project.core_app.ConstData
import com.project.core_app.menu_bottom_bar.model.CountryData

fun searchCountry(key: String): MutableList<CountryData> {
    val tempList = mutableListOf<CountryData>()
    ConstData.LIB_COUNTRIES. forEach {
        if (it.countryName.lowercase().contains(key.lowercase())) {
            tempList.add(it)
        }
    }
    return tempList
}