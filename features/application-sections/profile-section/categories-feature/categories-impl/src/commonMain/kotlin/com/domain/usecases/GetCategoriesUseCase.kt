package com.domain.usecases

import com.domain.repository.CategoryClientApi
import com.model.CategoryResponseModels

class GetCategoriesUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( ): List<CategoryResponseModels> {

        return client.getCategories ( )
    }
}