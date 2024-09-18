package org.example.project.presentation.core.app.domain

class CheckAuthorizationStatusUseCase( private val sharedPrefs: SharedPrefsApi){

    fun excecute(): AuthorizationStatus {
      return if((sharedPrefs.getUserNumber()?:"").isBlank())
           AuthorizationStatus.WAS_NO_AUTHORIZATION
        else AuthorizationStatus.WAS_AUTHORIZATION
    }

}


