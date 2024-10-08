package com.project.`outhorization-screen-impl`.screens.check_sms.viewmodel

import com.project.`outhorization-screen-api`.UserStatus

data class CheckSMSState(
    val fullSmsCode:String = "",
    val fieldSms1:String? = null,
    val fieldSms2:String? = null,
    val fieldSms3:String? = null,
    val fieldSms4:String? = null,
    val isCorrectSMS:Boolean = true,
    val secondSMSText:String = "",
    val textWrong:String = "",
    val name:String = "",
    val userStatus: UserStatus = UserStatus.NEW,
    val statusSMS: StatusSMS = StatusSMS.CORRECT_SMS

)

enum class StatusSMS{
    INCORRECT_SMS,CORRECT_SMS
}
