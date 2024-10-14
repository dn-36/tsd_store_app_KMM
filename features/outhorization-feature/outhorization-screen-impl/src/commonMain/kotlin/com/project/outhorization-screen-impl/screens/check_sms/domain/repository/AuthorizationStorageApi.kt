package com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository

interface AuthorizationStorageApi {

    fun saveUserNumber(username: String)

    fun getUserNumber(): String?

}
