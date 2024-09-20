package org.example.project.presentation.core.app

import org.example.project.presentation.core.app.domain.CheckAuthorizationStatusUseCase
import org.example.project.presentation.core.app.viewmodel.AppViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

 val appModule: Module
    get() = module {
        factory {
           AppViewModel(get())
        }
        factory {
           CheckAuthorizationStatusUseCase(get())
        }

    }