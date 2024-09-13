package org.example.project.presentation.core

import org.example.project.platformModule
import org.example.project.presentation.feature.authorization.core.auhtorizationFeatureModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration



fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {


        config?.invoke(this)
        modules(listOf(auhtorizationFeatureModule, platformModule))
    }
}