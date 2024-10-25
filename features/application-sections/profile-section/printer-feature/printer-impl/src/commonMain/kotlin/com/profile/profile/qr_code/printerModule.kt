package com.profile.profile.qr_code

import com.profile.profile.qr_code.screens.product_search.datasource.RepositoryProductImpl
import com.profile.profile.qr_code.screens.product_search.domain.GetTitleProductUseCase
import com.profile.profile.qr_code.screens.product_search.domain.RepositoryProductAPI
import com.project.core_app.ConstData
import com.project.`printer-api`.PrinterScreensApi
import org.koin.dsl.module
import product_network.ProductApiClient

val printerScreenModule = module {
    factory { GetTitleProductUseCase(get()) }
    factory { RepositoryProductImpl(
        get()
    ) as RepositoryProductAPI
    }
    factory { ProductApiClient(ConstData.TOKEN) }
    factory {
        PrinterScreensImpl() as PrinterScreensApi
    }

}