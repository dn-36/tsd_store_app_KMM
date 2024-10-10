package com.profile.profile.qr_code.core

import com.project.core_app.ConstData
import com.profile.profile.qr_code.screens.product_search.datasource.RepositoryProductImpl
import com.profile.profile.qr_code.screens.product_search.domain.GetTitleProductUseCase
import com.profile.profile.qr_code.screens.product_search.domain.RepositoryProductAPI

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