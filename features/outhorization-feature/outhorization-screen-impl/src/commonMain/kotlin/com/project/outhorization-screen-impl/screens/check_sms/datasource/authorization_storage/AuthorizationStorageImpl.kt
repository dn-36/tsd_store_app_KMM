package com.project.`outhorization-screen-impl`.screens.check_sms.datasource.authorization_storage

import com.project.`local-storage`.`profile-storage`.ProfileValueStorageApi
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationStorageApi


class AuthorizationStorageImpl(private val sharedPrefs: ProfileValueStorageApi):
    AuthorizationStorageApi {
    override suspend fun saveUserNumber(username: String) {
        sharedPrefs.saveCurrentNumber(username)
    }

    override suspend fun getUserNumber(): String {
       return sharedPrefs.getCurrentNumber()?:""
    }



}