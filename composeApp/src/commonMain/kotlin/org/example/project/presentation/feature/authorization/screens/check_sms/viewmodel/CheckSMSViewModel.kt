package org.example.project.presentation.feature.authorization.screens.check_sms.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.project.presentation.core.StringRes

class CheckSMSViewModel:ViewModel(){

    var state = MutableStateFlow(CheckSMSState())
        private set
   private var  number:String = ""
    fun processIntent(intent: CheckSMSEvent){
        when(intent){
         is CheckSMSEvent.InputName -> {
           state.value = state.value.copy(name = intent.name)
         }
         is CheckSMSEvent.InitDataView -> {
           state.value = state.value.copy(
               userStatus = intent.status
           )
         }
         is CheckSMSEvent.InputSMS -> {

            state.value = state.value.copy(fullText = intent.number)
            println(number)

            val text =  state.value.fullText
             when(state.value.fullText.length){
                 0-> state.value = state.value.copy(
                     fieldSms1 = "",fieldSms2 = "",
                     fieldSms3 = "",fieldSms4 = ""
                     )
                 1->state.value = state.value.copy(
                     fieldSms1 = text[0].toString(),fieldSms2 = "",
                     fieldSms3 = "",fieldSms4 = ""
                 )
                 2->state.value = state.value.copy(
                     fieldSms1 = text[0].toString(),fieldSms2 = text[1].toString(),
                     fieldSms3 = "",fieldSms4 = ""
                 )
                 3->state.value = state.value.copy(
                     fieldSms1 = text[0].toString(),fieldSms2 = text[1].toString(),
                     fieldSms3 = text[2].toString(),fieldSms4 = ""
                 )
                 4->state.value = state.value.copy(
                     fieldSms1 = text[0].toString(),fieldSms2 = text[1].toString(),
                     fieldSms3 = text[2].toString(),fieldSms4 = text[3].toString()
                 )
             }
         }
         is CheckSMSEvent.SendSms -> {
             intent.coroutineScope.cancel()
             intent.coroutineScope.launch {
                 var secondTimer = 60

                 repeat(61){
                     delay(1000)
                     --secondTimer
                     if(secondTimer!=0){
                         state.value = state.value.copy(secondSMSText =
                         StringRes.SMS_WAIT_MESSAGE(
                             secondTimer
                         )
                         )
                     }else{
                         state.value = state.value.copy(secondSMSText =
                             "если SMS не пришло, то попробуйте повторно отправить SMS"
                         )
                     }
                 }
             }
         }
        }
    }


}