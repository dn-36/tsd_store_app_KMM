package org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel

import com.module.core.presentation.screens.entering_number.model.CountryData
import kotlinx.coroutines.CoroutineScope

sealed class EnteringNumberEvent {
    data class InputNumber(val number:String): EnteringNumberEvent()
    data class SelectCountry(val countryData: CountryData): EnteringNumberEvent()
    object OpenSelectionCodeCountryDialog: EnteringNumberEvent()
    data class SendNumber(val number:String,val scope:CoroutineScope): EnteringNumberEvent()
    object CloseDialog: EnteringNumberEvent()

}