package org.example.project.presentation.feature.qr_code.core

import org.example.project.presentation.core.ConstData
import org.example.project.presentation.feature.qr_code.screens.product_search.datasource.RepositoryProductImpl
import org.example.project.presentation.feature.qr_code.screens.product_search.domain.GetTitleProductUseCase
import org.example.project.presentation.feature.qr_code.screens.product_search.domain.RepositoryProductAPI

import org.koin.dsl.module
import product_network.ProductApiClient

val qrCodeFeatureModule = module{
    factory { GetTitleProductUseCase(get()) }
    factory { RepositoryProductImpl(
        get()
    ) as RepositoryProductAPI
    }
    factory { ProductApiClient(ConstData.TOKEN) }
}