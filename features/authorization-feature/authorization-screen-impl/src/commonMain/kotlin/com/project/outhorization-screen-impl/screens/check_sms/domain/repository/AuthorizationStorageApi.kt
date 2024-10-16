package com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository

interface AuthorizationStorageApi {

    suspend fun saveUserNumber(username: String)

    suspend fun getUserNumber(): String?

    suspend fun saveToken(token:String)

}
