package org.example.project

import com.module.core.AuthorizationClient
import com.module.core.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

/*
actual val platformDi: Module = module {     factory {
        createHttpClient(OkHttp.create())
    } }*/
actual val platformModule: Module = module {
    factory {
     createHttpClient(OkHttp.create())
    }
}