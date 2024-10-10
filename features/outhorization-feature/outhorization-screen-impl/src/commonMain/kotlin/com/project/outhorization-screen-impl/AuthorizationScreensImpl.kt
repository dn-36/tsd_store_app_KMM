package com.project.`outhorization-screen-impl`

import cafe.adriel.voyager.core.screen.Screen
import com.project.`outhorization-screen-api`.AuthorizationScreensApi
import com.project.`outhorization-screen-impl`.screens.check_sms.ui.CheckSMSScreen
import com.project.`outhorization-screen-api`.UserStatus
import org.example.project.presentation.feature.authorization.screens.entering_number.ui.EnteringAnumberScreen


class AuthorizationScreensImpl:AuthorizationScreensApi {

    override fun checkSms(number : String,statusSMS: UserStatus): Screen {
        return CheckSMSScreen(number,statusSMS)
    }



    override fun enteringNumber(): Screen {
        return EnteringAnumberScreen()
    }
}