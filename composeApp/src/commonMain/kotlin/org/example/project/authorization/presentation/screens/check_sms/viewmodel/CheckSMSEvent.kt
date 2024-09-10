package org.tsdstore.project.feature.authorization.presentation.screens.check_sms.viewmodel

import kotlinx.coroutines.CoroutineScope


sealed class CheckSMSEvent {
    data class InputSMS(val number:String): CheckSMSEvent()
    data class SendSms(val number:String): CheckSMSEvent()
    data class UpdateSecondTimer(val coroutineScope: CoroutineScope): CheckSMSEvent()


}