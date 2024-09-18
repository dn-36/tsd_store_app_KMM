package org.example.project

import io.ktor.client.engine.okhttp.OkHttp
import networking.createHttpClient
import org.example.project.presentation.core.app.domain.SharedPrefsApi
import org.koin.core.module.Module
import org.koin.dsl.module
import shared_prefs.CreateContextPlatform
import shared_prefs.SharedPreferences


actual val platformModule: Module = module {
    factory {
        createHttpClient(OkHttp.create())
    }
    factory {
            SharedPreferences(get())
        }
        factory {
            CreateContextPlatform(get()).get()
        }
    }

