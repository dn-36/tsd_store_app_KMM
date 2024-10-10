package com.project.network
import io.ktor.client.engine.ios.Ios
import io.ktor.client.engine.HttpClientEngine

actual val httpClientEngine: HttpClientEngine
    get() = Ios.create()