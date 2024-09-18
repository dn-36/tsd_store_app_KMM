package org.example.project

import io.ktor.client.engine.darwin.Darwin
import networking.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

import shared_prefs.CreateContextPlatform
import shared_prefs.SharedPreferences


actual val platformModule: Module
    get() = module {
        factory {
            createHttpClient(Darwin.create())
        }
        factory {
            SharedPreferences(get())
        }
        factory {
           CreateContextPlatform().get()
        }
    }


