package org.example.project.app.viewmodel

import org.example.project.app.domain.AuthorizationStatus

data class AppState(
    val authorizationStatus: AuthorizationStatus = AuthorizationStatus.LOADING
)

