package org.example.project.presentation.feature.authorization.screens.check_sms.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.core.StringRes
import org.example.project.presentation.feature.authorization.core.repository_impl.authorization_client.UserStatus
import org.example.project.presentation.feature.authorization.screens.check_sms.domain.CheckCodeSmsUseCase
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.ui.QRCodeMenuScreen

class CheckSMSViewModel(val checkCodeSmsUseCase: CheckCodeSmsUseCase):ViewModel(){

    var state = MutableStateFlow(CheckSMSState())
        private set
    fun processIntent(intent: CheckSMSEvent){
        when(intent){
         is CheckSMSEvent.InputName -> {
           state.value = state.value.copy(name = intent.name)
         }
         is CheckSMSEvent.InitDataView -> {

             intent.scope.launch {
                 var secondTimer = 60
                 repeat(61){
                     delay(1000)
                     --secondTimer
                     if(secondTimer != 0){
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

           state.value = state.value.copy(
               userStatus = intent.status
           )
         }
         is CheckSMSEvent.InputSMS -> {

            state.value = state.value.copy(fullSmsCode = intent.number)

            val text =  state.value.fullSmsCode
             when(state.value.fullSmsCode.length){
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
             println("//////////////////////////// test ////////////////////////////////\n\n")
             println("sms =  ${state.value.fullSmsCode}\n " +
                     "token = 19222\n" +
                      "number = ${intent.number}\n" +
                       "name = ${state.value.name}\n")
             println("\n\n//////////////////////////// test ////////////////////////////////")
             when{
                 ( state.value.name.isBlank() && state.value.userStatus == UserStatus.NEW) -> {
                     state.value =
                         state.value.copy(textWrong =
                         "Заполните поля имя",isCorrectSMS = false)
                     return

                 }
                 state.value.fullSmsCode.isBlank() -> {
                     state.value =
                         state.value.copy(
                             textWrong =
                             "Заполните поля SMS", isCorrectSMS = false
                         )
                     return
                 }
                 else ->{
                     state.value.copy(isCorrectSMS = false)
                 }
             }
             //intent.coroutineScope.cancel()
             intent.coroutineScope.launch {
                 checkCodeSmsUseCase.excecute(
                     state.value.fullSmsCode,
                     "192221",
                     intent.number,
                     state.value.name,
                     actionOnSuccess = {NavigatorComponent.navigator!!.push(QRCodeMenuScreen)},
                     {state.value = state.value.copy(statusSMS = StatusSMS.INCORRECT_SMS)}
                 )

             }

         }
        }
    }


}