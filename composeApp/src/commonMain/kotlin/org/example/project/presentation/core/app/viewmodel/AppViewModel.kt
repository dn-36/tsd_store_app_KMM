package org.example.project.presentation.core.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.project.presentation.core.app.domain.AuthorizationStatus
import org.example.project.presentation.core.app.domain.CheckAuthorizationStatusUseCase

class AppViewModel(
    private val checkAuthorizationStatus: CheckAuthorizationStatusUseCase
) : ViewModel(){

    val state  = MutableStateFlow(AppState())

    fun proccesIntent(intent: AppIntent){
        when(intent){
            is AppIntent.SetScreenIntent -> {
                when(checkAuthorizationStatus.excecute()){
                AuthorizationStatus.LOADING -> {
                state.value = state.value.copy(AuthorizationStatus.LOADING)
                }
                AuthorizationStatus.WAS_NO_AUTHORIZATION ->{
                    state.value = state.value.copy(AuthorizationStatus.WAS_NO_AUTHORIZATION)
                }
                AuthorizationStatus.WAS_AUTHORIZATION -> {
                    state.value = state.value.copy(AuthorizationStatus.WAS_AUTHORIZATION)
                }
                }
            }
        }
    }
}