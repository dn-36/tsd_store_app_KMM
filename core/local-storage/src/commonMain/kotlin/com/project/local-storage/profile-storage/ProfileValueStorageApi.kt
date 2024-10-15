package com.project.`local-storage`.`profile-storage`

interface  ProfileValueStorageApi {
    suspend fun saveCurrentNumber( value: String)
    suspend fun getCurrentNumber(): String?
    suspend fun saveCurrentName( value: String)
    suspend fun getCurrentName(): String?
}




