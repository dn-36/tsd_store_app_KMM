package org.example.project

import com.project.network.authorization_network.createHttpClient
import com.project.`local-storage`.`outhorization-storage`.CreateContextPlatform
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platforModule: Module
    get() = module {
        factory {
            createHttpClient(Darwin.create())
        }
        factory {
            CreateContextPlatform().get()
        }
    }