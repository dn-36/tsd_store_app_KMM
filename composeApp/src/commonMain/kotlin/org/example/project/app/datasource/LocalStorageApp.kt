package com.project.core_app.app.datasource

import org.example.project.app.domain.LocalStorageAppApi
import com.project.`local-storage`.`profile-storage`.ProfileValueStorageApi


class LocalStorageApp(private val sharedPrefs: ProfileValueStorageApi): LocalStorageAppApi {
    override suspend fun number(): String = sharedPrefs.getCurrentNumber()?:""
    override suspend fun saveNumber(number: String) {
      sharedPrefs.saveCurrentNumber (number)
    }


}