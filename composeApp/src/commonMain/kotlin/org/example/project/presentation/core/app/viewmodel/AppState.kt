package org.example.project.presentation.core.app.viewmodel

import org.example.project.presentation.core.app.domain.AuthorizationStatus

data class AppState(
    val authorizationStatus: AuthorizationStatus = AuthorizationStatus.LOADING
)

