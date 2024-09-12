package org.example.project

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import com.module.core.core.networking.InsultCensorClient
import com.module.core.core.networking.createHttpClient

fun MainViewController() = ComposeUIViewController {
    App( client = remember {
        InsultCensorClient(createHttpClient(Darwin.create()))
    }) }