package org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel

import org.example.project.presentation.feature.authorization.screens.entering_number.model.CountryData

data class EnteringnumberState(
    val countryData: CountryData = CountryData(),
    val number:String = "",
    val isCorrectNumberStatus:Boolean = true,
    val isOpenSelectionCodeCountryDialog:Boolean = false,
    val isLoadingScreen:Boolean = false
)
