package com.project.`local-storage`.`profile-storage`

import android.content.Context
import com.project.`local-storage`.`profile-storage`.KeyValueStorageApi

class StorageAndroid(context:Context) : KeyValueStorageApi {

    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override suspend fun saveCurrentNumber(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override suspend fun saveCurrentName(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override suspend fun getCurrentNumber(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override suspend fun getCurrentName(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}
