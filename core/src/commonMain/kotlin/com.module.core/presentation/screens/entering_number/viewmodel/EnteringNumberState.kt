package org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel

import com.module.core.presentation.screens.entering_number.model.CountryData

data class EnteringnumberState(
    val countryData: CountryData = CountryData(),
    val number:String = "",
    val isCorrectNumberStatus:Boolean = true,
    val isOpenSelectionCodeCountryDialog:Boolean = false
)
