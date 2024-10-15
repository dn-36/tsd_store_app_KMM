package com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository

import kotlinx.coroutines.CoroutineScope
import com.project.`outhorization-screen-api`.UserStatus

interface AuthorizationClientAPI {
    companion object{
        var userStatus: UserStatus = UserStatus.NEW
    }

    suspend fun sendNumber(
                   number:String,
                   scope:CoroutineScope,
                   actionOnSuccess:(Boolean)->Unit,
                   actionOnError:()->Unit
    )

    suspend fun checkSMS(
        code: String,
        token:String,
        number:String,
        name:String,
        actionOnSuccess:suspend ()->Unit,
        actionOnError:()->Unit
    )
}