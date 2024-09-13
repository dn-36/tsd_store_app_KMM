package org.example.project.presentation.feature.authorization.screens.check_sms.doman

import org.example.project.presentation.feature.authorization.screens.entering_number.domain.AuthorizationClientAPI

class CheckCodeSmsUseCase  (private val client: AuthorizationClientAPI) {

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
            actionOnSuccess,
            actionOnError
        )
    }
}

