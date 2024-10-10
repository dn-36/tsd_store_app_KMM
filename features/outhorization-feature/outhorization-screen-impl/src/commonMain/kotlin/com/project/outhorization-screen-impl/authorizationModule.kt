package com.project.`outhorization-screen-impl`


import com.project.`outhorization-screen-api`.AuthorizationScreensApi
import com.project.`outhorization-screen-impl`.core.datasource.`authorization-client`.AuthorizationClientImpl
import com.project.`outhorization-screen-impl`.screens.check_sms.datasource.authorization_storage.AuthorizationStorageImpl
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI
import com.project.`outhorization-screen-impl`.screens.entering_number.domain.SendNumberUseCase
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.CheckCodeSmsUseCase
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationStorageApi
import com.project.`outhorization-screen-impl`.screens.check_sms.viewmodel.CheckSMSViewModel
import org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel.EnteringNumberViewModel
import org.koin.dsl.module


val authorizationModule = module {
    factory {
        AuthorizationScreensImpl() as AuthorizationScreensApi
    }
    factory { EnteringNumberViewModel(get()) }
    factory { CheckSMSViewModel(get()) }
    factory { CheckCodeSmsUseCase(get(),get()) }
    factory { AuthorizationStorageImpl(get()) as AuthorizationStorageApi }
    factory { SendNumberUseCase(get()) }
    single { AuthorizationClientImpl(get()) as AuthorizationClientAPI }
  /*  single { AuthorizationClient(get()) }
    single { LocalStorageApp(get()) as AuthorizationStorageApi }*/
}
