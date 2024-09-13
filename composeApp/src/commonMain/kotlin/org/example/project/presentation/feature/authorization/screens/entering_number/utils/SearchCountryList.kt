package org.example.project.presentation.feature.authorization.screens.entering_number.utils

import org.example.project.presentation.core.ConstData
import com.module.core.presentation.screens.entering_number.model.CountryData

fun searchCountry(key: String): MutableList<CountryData> {
    val tempList = mutableListOf<CountryData>()
    ConstData.LIB_COUNTRIES. forEach {
        if (it.countryName.lowercase().contains(key.lowercase())) {
            tempList.add(it)
        }
    }
    return tempList
}