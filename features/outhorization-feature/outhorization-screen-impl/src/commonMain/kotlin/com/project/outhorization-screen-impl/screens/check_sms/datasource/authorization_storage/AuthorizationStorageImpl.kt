package com.project.`outhorization-screen-impl`.screens.check_sms.datasource.authorization_storage

import com.project.`local-storage`.`outhorization-storage`.AuthorizationStorage
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationStorageApi


class AuthorizationStorageImpl(private val sharedPrefs: AuthorizationStorage):
    AuthorizationStorageApi {
    override fun saveUserNumber(username: String) {
        sharedPrefs.saveUsername(username)
    }

    override fun getUserNumber(): String {
       return sharedPrefs.getUsername()?:""
    }



}