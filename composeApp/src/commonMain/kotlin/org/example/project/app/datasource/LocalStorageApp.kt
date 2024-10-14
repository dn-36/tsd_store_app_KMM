package com.project.core_app.app.datasource

import com.project.core_app.app.domain.LocalStorageAppApi
import com.project.`local-storage`.`outhorization-storage`.AuthorizationStorage


class LocalStorageApp(private val sharedPrefs: AuthorizationStorage): LocalStorageAppApi {
    override fun number(): String = sharedPrefs.getUsername()?:""
    override fun saveNumber(number: String) {
      sharedPrefs.saveUsername(number)
    }

}