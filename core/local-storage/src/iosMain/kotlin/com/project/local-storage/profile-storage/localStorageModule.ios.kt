package com.project.`local-storage`.`profile-storage`

import com.project.`local-storage`.`outhorization-storage`.AuthorizationStorage
import com.project.`local-storage`.`outhorization-storage`.CreateContextPlatform
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val localStorageModule: Module = module{
       factory {
           StorageIos() as KeyValueStorageApi
       }
    single {
        AuthorizationStorage(get())
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