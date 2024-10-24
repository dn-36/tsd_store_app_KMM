package com.project.`local-storage`.`profile-storage`

interface  SharedPrefsApi {

    suspend fun saveCurrentNumber( value: String)
    suspend fun getCurrentNumber(): String?
    suspend fun saveCurrentName( value: String)
    suspend fun getCurrentName(): String?
    suspend fun saveToken(value:String)

    suspend fun getToken():String?
}




