package org.example.project.presentation.core.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.project.presentation.core.app.domain.AuthorizationStatus
import org.example.project.presentation.core.app.domain.CheckAuthorizationStatusUseCase

class AppViewModel(
    private val checkAuthorizationStatus: CheckAuthorizationStatusUseCase
) : ViewModel(){

    var state  = MutableStateFlow(AppState())

    fun proccesIntent(event: AppEvent){
        when(event){
            is AppEvent.InitScreenEvent -> {
                if( checkAuthorizationStatus.excecute() ==
                    AuthorizationStatus.WAS_AUTHORIZATION){
                    state.value = state.value.copy(true)
                }else{
                    state.value = state.value.copy(false)
                }
            }
        }
    }
}