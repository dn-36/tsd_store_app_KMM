package org.example.project.presentation.feature.authorization.screens.check_sms.domain

import org.example.project.presentation.feature.authorization.core.AuthorizationClientAPI
import org.example.project.presentation.core.app.domain.SharedPrefsApi

class CheckCodeSmsUseCase  (
    private val client: AuthorizationClientAPI,
    private val sharedPrefs: SharedPrefsApi
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

