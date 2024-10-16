package com.project.core_app.app.domain

import org.example.project.app.domain.AuthorizationStatus
import org.example.project.app.domain.LocalStorageAppApi


class CheckAuthorizationStatusUseCase (private val sharedPrefs: LocalStorageAppApi ) {

    suspend fun excecute (): AuthorizationStatus {

      return if((sharedPrefs.number()?:"").isBlank())

          AuthorizationStatus.WAS_NO_AUTHORIZATION

        else AuthorizationStatus.WAS_AUTHORIZATION

    }

}



