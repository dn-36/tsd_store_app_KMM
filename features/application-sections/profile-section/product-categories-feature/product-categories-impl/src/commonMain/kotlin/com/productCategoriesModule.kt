package com

import com.datasousrce.ProductCategoriesClientImpl
import com.domain.repository.ProductCategoriesClientApi
import com.domain.usecases.GeListCategoriesUseCase
import com.project.network.category_network.CategoryClient
import com.viewmodel.ProductCategoriesViewModel
import org.koin.dsl.module

val productCategoriesModule = module {

    factory { ProductCategoriesScreenImpl() as ProductCategoriesScreenApi }

    factory { ProductCategoriesClientImpl(get(), get()) as ProductCategoriesClientApi }

    factory { ProductCategoriesViewModel(get()) }

    factory { GeListCategoriesUseCase(get()) }

    factory { CategoryClient() }

}