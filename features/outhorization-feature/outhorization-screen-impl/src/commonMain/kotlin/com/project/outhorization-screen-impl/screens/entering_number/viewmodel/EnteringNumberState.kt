package org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel

import com.project.core_app.menu_bottom_bar.model.CountryData

data class EnteringnumberState(
    val countryData: CountryData = CountryData(),
    val number:String = "",
    val isCorrectNumberStatus:Boolean = true,
    val isOpenSelectionCodeCountryDialog:Boolean = false,
    val isLoadingScreen:Boolean = false
)
