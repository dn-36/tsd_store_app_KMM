package com.project.`outhorization-screen-api`

import cafe.adriel.voyager.core.screen.Screen

interface AuthorizationScreensApi{
    fun checkSms(number : String,statusSMS: UserStatus):Screen
    fun enteringNumber():Screen
}

