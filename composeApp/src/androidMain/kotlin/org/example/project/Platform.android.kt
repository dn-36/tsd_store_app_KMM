package org.example.project

import android.content.SharedPreferences
import android.os.Build
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.http.auth.HttpAuthHeader
import networking.createHttpClient
import org.example.project.presentation.feature.authorization.core.SharedPrefsApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.KoinContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin


actual val platformModule: Module = module {
    factory {
        createHttpClient(OkHttp.create())
    }
    single {
         SharedPreferences(androidContext()) as SharedPrefsApi
    }
}
actual val sharedPrefsImpl: SharedPrefsApi = getKoin().get()

