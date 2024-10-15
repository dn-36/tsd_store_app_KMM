package com.project.`outhorization-screen-impl`


import com.project.network.authorization_network.AuthorizationClient
import com.project.network.authorization_network.createHttpClient
import com.project.`outhorization-screen-api`.AuthorizationScreensApi
import com.project.`outhorization-screen-impl`.core.datasource.`authorization-client`.AuthorizationClientImpl
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.repository.AuthorizationClientAPI
import com.project.`outhorization-screen-impl`.screens.entering_number.domain.SendNumberUseCase
import com.project.`outhorization-screen-impl`.screens.check_sms.domain.CheckCodeSmsUseCase
import com.project.`outhorization-screen-impl`.screens.check_sms.viewmodel.CheckSMSViewModel
import org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel.EnteringNumberViewModel
import org.koin.dsl.module
import product_network.httpClientEngine


val authorizationModule = module {
    factory {
        AuthorizationScreensImpl() as AuthorizationScreensApi
    }
    factory { EnteringNumberViewModel(get()) }
    factory { CheckSMSViewModel(get()) }
    factory { CheckCodeSmsUseCase(get(),get()) }
    //factory { AuthorizationStorageImpl(get()) as AuthorizationStorageApi }
    factory { SendNumberUseCase(get()) }
    factory { AuthorizationClientImpl(get()) as AuthorizationClientAPI }
   // factory { AuthorizationClient(get())}

  /*  single { AuthorizationClient(get()) }*/

}
