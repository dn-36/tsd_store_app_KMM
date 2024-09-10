package org.tsdstore.project.feature.authorization.presentation.utils

import org.tsdstore.project.feature.authorization.presentation.ConstData
import org.example.project.authorization.presentation.screens.entering_number.model.CountryData

fun searchCountry(key: String): MutableList<CountryData> {
    val tempList = mutableListOf<CountryData>()
    ConstData.LIB_COUNTRIES. forEach {
        if (it.countryName.lowercase().contains(key.lowercase())) {
            tempList.add(it)
        }
    }
    return tempList
}