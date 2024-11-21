package com

import org.koin.dsl.module

val productsMenuModule = module {

    factory { ProductsMenuScreenImpl() as ProductsMenuScreenApi }
}