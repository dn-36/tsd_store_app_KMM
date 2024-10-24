package com.project.`outhorization-screen-impl`.screens.check_sms.datasource.`authorization-client`

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationStorageApi


class AuthorizationStorageImpl(private val sharedPrefsApi: SharedPrefsApi):AuthorizationStorageApi {
    override suspend fun saveUserNumber(usernumber: String) {
        sharedPrefsApi.saveCurrentNumber(usernumber)
    }

    override suspend fun getUserNumber(): String?  = sharedPrefsApi.getCurrentNumber()


    override suspend fun saveToken(token: String) {

       sharedPrefsApi.saveToken(token)
    }

}