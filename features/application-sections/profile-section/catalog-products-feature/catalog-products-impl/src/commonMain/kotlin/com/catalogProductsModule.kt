package com

import com.project.network.category_network.CategoryClient
import org.koin.dsl.module

val catalogProductsModule = module {

    factory { CatalogProductsScreenImpl() as CatalogProductsScreenApi }

}