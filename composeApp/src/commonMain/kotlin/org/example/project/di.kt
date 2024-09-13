package org.example.project

import com.module.core.AuthorizationClient
import org.example.project.presentation.feature.authorization.screens.entering_number.domain.SendNumberUseCase
import org.example.project.presentation.feature.authorization.screens.entering_number.domain.AuthorizationClientAPI
import org.example.project.presentation.feature.authorization.screens.entering_number.repository.AuthorizationClientIMPL
import org.example.project.presentation.feature.authorization.screens.entering_number.viewmodel.EnteringNumberViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    factory {
        EnteringNumberViewModel(get())
    }
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
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(commonModule,platformModule)
    }
}