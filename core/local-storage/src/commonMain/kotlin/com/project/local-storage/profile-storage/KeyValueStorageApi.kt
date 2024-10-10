package com.project.`local-storage`.`profile-storage`

interface  KeyValueStorageApi {
    suspend fun saveCurrentNumber(key: String, value: String)
    suspend fun getCurrentNumber(key: String): String?
    suspend fun saveCurrentName(key: String, value: String)
    suspend fun getCurrentName(key: String): String?
}




