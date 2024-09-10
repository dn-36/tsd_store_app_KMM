package org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel

import org.example.project.authorization.presentation.screens.entering_number.model.CountryData

data class EnteringnumberState(
    val countryData: CountryData = CountryData(),
    val number:String = "",
    val isCorrectNumberStatus:Boolean = true,
    val isOpenSelectionCodeCountryDialog:Boolean = false
)
