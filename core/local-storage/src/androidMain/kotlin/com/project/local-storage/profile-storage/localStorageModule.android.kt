package com.project.`local-storage`.`profile-storage`

import org.koin.core.module.Module
import org.koin.dsl.module

actual val localStorageModule: Module
    get() = module {
        single {
            StorageAndroid(get()) as SharedPrefsApi
        }
    }