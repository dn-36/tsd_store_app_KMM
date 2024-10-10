package com.project.network

import com.project.network.authorization_network.AuthorizationClient
import com.project.network.authorization_network.createHttpClient
import org.koin.dsl.module

val network_module = module {
   factory {
       httpClientEngine
   }
    factory {
        createHttpClient(get())
    }
    factory {
        AuthorizationClient(get())
    }
}