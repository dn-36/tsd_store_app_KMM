package org.example.project.app.moduls_di

import com.project.core_app.app.datasource.LocalStorageApp
import com.project.core_app.app.domain.CheckAuthorizationStatusUseCase
import org.example.project.app.domain.LocalStorageAppApi
import org.example.project.app.viewmodel.AppViewModel
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
        factory {
            LocalStorageApp(get()) as LocalStorageAppApi
        }
    }

