package com.project.`outhorization-screen-impl`.screens.check_sms.domain


import com.project.`local-storage`.`profile-storage`.ProfileValueStorageApi
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationStorageApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class CheckCodeSmsUseCase  (
    private val client: AuthorizationClientAPI,
    private val sharedPrefs: ProfileValueStorageApi
    ) {

    suspend fun excecute( code: String,
                          token:String,
                          number:String,
                          name:String,
                          actionOnSuccess:()->Unit,
                          actionOnError:()->Unit,
                          ){

        client.checkSMS (
            code,
            token,
            number,
            name,
            {

                    sharedPrefs.saveCurrentNumber(number)
                    actionOnSuccess()


            },
            actionOnError
        )
    }
}

