package org.example.project.presentation.feature.authorization.core

import networking.AuthorizationClient
import org.example.project.presentation.core.app.domain.SharedPrefsApi
import org.example.project.presentation.feature.authorization.screens.check_sms.domain.CheckCodeSmsUseCase
import org.example.project.presentation.feature.authorization.screens.check_sms.viewmodel.CheckSMSViewModel
import org.example.project.presentation.feature.authorization.screens.entering_number.domain.SendNumberUseCase
import org.example.project.presentation.feature.authorization.datasource.authorization_client.AuthorizationClientIMPL
import org.example.project.presentation.core.app.datasource.shared_prefs.SharedPrefsImpl
import org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel.EnteringNumberViewModel
import org.koin.dsl.module

val auhtorizationFeatureModule = module {
    factory { EnteringNumberViewModel(get()) }
    factory { CheckSMSViewModel(get()) }
    factory { CheckCodeSmsUseCase(get(),get()) }
    factory { SendNumberUseCase(get()) }
    single { AuthorizationClientIMPL(get()) as AuthorizationClientAPI }
    single { AuthorizationClient(get()) }
    single { SharedPrefsImpl(get()) as SharedPrefsApi }
}