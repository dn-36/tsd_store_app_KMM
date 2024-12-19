package com.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ProductCategoriesIntents {

    data class SetScreen( val coroutineScope: CoroutineScope ): ProductCategoriesIntents()

    object SelectCategory: ProductCategoriesIntents()

    object Back: ProductCategoriesIntents()

}