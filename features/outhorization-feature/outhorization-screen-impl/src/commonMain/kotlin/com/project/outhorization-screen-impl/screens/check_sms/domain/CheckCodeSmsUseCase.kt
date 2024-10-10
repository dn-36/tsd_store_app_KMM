package com.project.`outhorization-screen-impl`.screens.check_sms.domain


import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationStorageApi

class CheckCodeSmsUseCase  (
    private val client: AuthorizationClientAPI,
    private val sharedPrefs: AuthorizationStorageApi
    ) {

    suspend fun excecute( code: String,
                          token:String,
                          number:String,
                          name:String,
                          actionOnSuccess:()->Unit,
                          actionOnError:()->Unit){

        client.checkSMS (
            code,
            token,
            number,
            name,
            {
                sharedPrefs.saveUserNumber(number)
                actionOnSuccess()
            },
            actionOnError
        )
    }
}

