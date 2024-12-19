package com.domain.usecases

import com.domain.repository.ProductCategoriesClientApi
import com.model.CategoriesResponseModel

class GeListCategoriesUseCase (

    private val client: ProductCategoriesClientApi,

    ) {

    suspend fun execute ( ): List<CategoriesResponseModel> {

        return client.getCategories ()

    }
}