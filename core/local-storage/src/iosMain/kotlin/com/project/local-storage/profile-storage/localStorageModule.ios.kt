package com.project.`local-storage`.`profile-storage`

import org.koin.core.module.Module
import org.koin.dsl.module

actual val localStorageModule: Module = module{
       factory {
           StorageIos() as ProfileValueStorageApi
       }

    }
/*
actual val platformModule: Module
    get() = module {
        factory {
            createHttpClient(Darwin.create())
        }

        factory {
            CreateContextPlatform().get()
        }
    }*/