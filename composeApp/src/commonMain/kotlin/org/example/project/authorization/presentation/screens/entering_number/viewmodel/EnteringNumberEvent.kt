package org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel

import org.example.project.authorization.presentation.screens.entering_number.model.CountryData

sealed class EnteringNumberEvent {
    data class InputNumber(val number:String): EnteringNumberEvent()
    data class SelectCountry(val countryData: CountryData): EnteringNumberEvent()
    object OpenSelectionCodeCountryDialog: EnteringNumberEvent()
    data class SendNumber(val number:String): EnteringNumberEvent()
    object CloseDialog: EnteringNumberEvent()

}