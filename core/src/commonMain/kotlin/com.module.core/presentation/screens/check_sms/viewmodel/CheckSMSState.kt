package org.tsdstore.project.feature.authorization.presentation.screens.check_sms.viewmodel

data class CheckSMSState(
    val fullText:String = "",
    val fieldSms1:String? = null,
    val fieldSms2:String? = null,
    val fieldSms3:String? = null,
    val fieldSms4:String? = null,
    val isCorrectSMS:Boolean = true,
    val secondSMSText:String = "",
    val name:String = "",
    val statusSMS: StatusSMS = StatusSMS.REGISTER
)

enum class StatusSMS{
    INCORRECT_SMS,REGISTER,AHTURIZATION
}