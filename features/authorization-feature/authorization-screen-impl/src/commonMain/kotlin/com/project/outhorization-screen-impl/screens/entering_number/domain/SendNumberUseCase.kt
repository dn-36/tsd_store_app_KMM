package com.project.`outhorization-screen-impl`.screens.entering_number.domain

import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI
import kotlinx.coroutines.CoroutineScope
//AuthorizationClientAPI
class SendNumberUseCase (private val client: AuthorizationClientAPI) {
    suspend fun excecute(number:String,
                 scope: CoroutineScope,
                 actionOnSuccess:(status:Boolean)->Unit,
                 actionOnError:()->Unit){
        client.sendNumber(
            number,
            scope,
            actionOnSuccess,
            actionOnError
        )
    }
}