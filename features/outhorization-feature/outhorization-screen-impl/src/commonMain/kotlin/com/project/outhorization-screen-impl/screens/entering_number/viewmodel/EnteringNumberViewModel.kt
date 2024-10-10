package org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel

import androidx.lifecycle.ViewModel
import com.project.network.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.project.`outhorization-screen-impl`.screens.entering_number.domain.SendNumberUseCase
import com.project.`outhorization-screen-impl`.screens.check_sms.ui.CheckSMSScreen
import com.project.`outhorization-screen-api`.UserStatus

import org.tsdstore.project.feature.authorization.presentation.screens.entering_number.viewmodel.EnteringNumberEvent

class EnteringNumberViewModel(
    private val sendNumber: SendNumberUseCase,
) :ViewModel(){

    var state = MutableStateFlow(EnteringnumberState())


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
                 state.value = state.value.copy(isLoadingScreen = true)
                 intent.scope.launch {
                     sendNumber.excecute(
                         intent.number,
                         intent.scope,
                         {  state.value = state.value.copy(
                             isCorrectNumberStatus = true,
                             isLoadingScreen = false
                         )
                             Navigation.navigator!!.push(
                                 CheckSMSScreen(intent.number,
                                 if(it) UserStatus.REGISTERED else UserStatus.NEW
                             )
                             )},
                         {}
                         )
                 }
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