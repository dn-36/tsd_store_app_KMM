package org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.module.core.core.NavigatorView
import org.tsdstore.project.feature.authorization.presentation.screens.check_sms.CheckSMSScreen

class EnteringNumberViewModel:ViewModel(){

    var state = MutableStateFlow(EnteringnumberState())
        private set

    fun processIntent(intent: EnteringNumberEvent){
        when(intent){
         is EnteringNumberEvent.InputNumber -> {
             state.value = state.value.copy(number = intent.number)
         }
            is EnteringNumberEvent.OpenSelectionCodeCountryDialog ->{
                state.value = state.value.copy(isOpenSelectionCodeCountryDialog = true)
            }

         is EnteringNumberEvent.SendNumber -> {
             if(state.value.number.length>=6) {
                 state.value = state.value.copy(isCorrectNumberStatus = true)
                 NavigatorView.navigator.push(CheckSMSScreen(intent.number))
             }else
                 state.value = state.value.copy(isCorrectNumberStatus = false)
         }

         is EnteringNumberEvent.SelectCountry -> {
             state.value = state.value.copy(
                 countryData = intent.countryData
             )
         }
         is EnteringNumberEvent.CloseDialog -> {
             state.value = state.value.copy(isOpenSelectionCodeCountryDialog = false)
         }
            else ->{}
        }
    }

}