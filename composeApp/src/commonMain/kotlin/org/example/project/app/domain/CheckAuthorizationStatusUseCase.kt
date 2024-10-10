package com.project.core_app.app.domain

import org.example.project.app.domain.AuthorizationStatus


class CheckAuthorizationStatusUseCase( private val sharedPrefs: LocalStorageAppApi){

    fun excecute(): AuthorizationStatus {
      return if((sharedPrefs.number()?:"").isBlank())
          AuthorizationStatus.WAS_NO_AUTHORIZATION
        else AuthorizationStatus.WAS_AUTHORIZATION
    }

}



