package com.profile.profile.qr_code

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val printerPlatformModule: Module
    get() = module {
    factory {
      //  createHttpClient(Darwin.create())
    }
    factory {
       // SharedPreferences(get())
    }
    factory {
       // CreateContextPlatform().get()
    }
}