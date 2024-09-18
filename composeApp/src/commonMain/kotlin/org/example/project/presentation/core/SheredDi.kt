package org.example.project.presentation.core

import org.example.project.platformModule
import org.example.project.presentation.core.app.appModule
import org.example.project.presentation.feature.authorization.core.auhtorizationFeatureModule
import org.example.project.presentation.feature.qr_code_main.screens.core.qrCodeFeatureModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration



fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            qrCodeFeatureModule,
            auhtorizationFeatureModule,
            platformModule,
            appModule
        )
    }
}