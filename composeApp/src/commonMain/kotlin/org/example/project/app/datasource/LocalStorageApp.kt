package org.example.project.app.datasource

import org.example.project.app.domain.LocalStorageAppApi
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi


class LocalStorageApp (private val sharedPrefs: SharedPrefsApi ): LocalStorageAppApi {

    override suspend fun number(): String = sharedPrefs.getCurrentNumber()?:""

    override suspend fun saveNumber(number: String) {

      sharedPrefs.saveCurrentNumber (number)

    }

    override suspend fun saveToken(token: String) {
        sharedPrefs.saveToken(token)
    }

}