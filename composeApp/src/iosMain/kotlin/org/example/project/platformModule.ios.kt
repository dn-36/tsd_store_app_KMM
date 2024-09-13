package org.example.project

import com.module.core.createHttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        factory {
            createHttpClient(Darwin.create())
        }
    }