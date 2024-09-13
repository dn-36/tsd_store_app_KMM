package org.example.project

import io.ktor.client.engine.darwin.Darwin
import networking.createHttpClient
import org.example.project.presentation.feature.authorization.core.SharedPrefsApi
import org.example.project.presentation.feature.authorization.datasource.sharaedprefs.SharedPrefsIImpl
import org.koin.core.module.Module
import org.koin.dsl.module



actual val platformModule: Module
    get() = module {
        factory {
            createHttpClient(Darwin.create())
        }
    }
actual val sharedPrefsImpl: SharedPrefsApi
    get() = object : SharedPrefsApi{
        override fun saveUserNumber(username: String) {

        }

        override fun getUserNumber() = "+79963799050"

    }