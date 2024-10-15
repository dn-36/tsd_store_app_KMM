package com.project.`local-storage`.`profile-storage`

import android.content.Context

class StorageAndroid(context:Context) : ProfileValueStorageApi {
    val NUMBER:String = "NUMBER"
    val NAME:String = "NAME"
    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override suspend fun saveCurrentNumber( value: String) {
        sharedPreferences.edit().putString(NUMBER, value).apply()
    }

    override suspend fun saveCurrentName(value: String) {
        sharedPreferences.edit().putString(NAME, value).apply()
    }

    override suspend fun getCurrentNumber(): String? {
        return sharedPreferences.getString(NUMBER, null)
    }

    override suspend fun getCurrentName(): String? {
        return sharedPreferences.getString(NAME, null)

    }
}
