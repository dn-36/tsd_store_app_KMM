package org.example.project.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.project.app.domain.AuthorizationStatus
import com.project.core_app.app.domain.CheckAuthorizationStatusUseCase

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