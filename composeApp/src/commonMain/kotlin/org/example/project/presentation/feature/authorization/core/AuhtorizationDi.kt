package org.example.project.presentation.feature.authorization.core

import networking.AuthorizationClient
import org.example.project.presentation.feature.authorization.screens.check_sms.doman.CheckCodeSmsUseCase
import org.example.project.presentation.feature.authorization.screens.check_sms.viewmodel.CheckSMSViewModel
import org.example.project.presentation.feature.authorization.screens.entering_number.domain.AuthorizationClientAPI
import org.example.project.presentation.feature.authorization.screens.entering_number.domain.SendNumberUseCase
import org.example.project.presentation.feature.authorization.core.repository_impl.authorization_client.AuthorizationClientIMPL
import org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel.EnteringNumberViewModel
import org.koin.dsl.module

val auhtorizationFeatureModule = module {
    factory {
        EnteringNumberViewModel(get())
    }
    factory { CheckSMSViewModel(get()) }
    factory { CheckCodeSmsUseCase(get()) }

    factory {
        SendNumberUseCase(get())
    }

    single {
        AuthorizationClientIMPL(get()) as AuthorizationClientAPI
    }
    single {
        AuthorizationClient(get())
    }
}