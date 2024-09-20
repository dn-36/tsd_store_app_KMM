package org.example.project.presentation.feature.authorization.screens.check_sms.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.example.project.presentation.feature.authorization.datasource.authorization_client.UserStatus


sealed class CheckSMSEvent {
    data class InputSMS(val number:String): CheckSMSEvent()
    data class SendSms(
      val number:String,
      val coroutineScope: CoroutineScope): CheckSMSEvent()
    data class InputName(val name: String):CheckSMSEvent()
    data class InitDataView(val status: UserStatus, val scope: CoroutineScope):CheckSMSEvent()

}