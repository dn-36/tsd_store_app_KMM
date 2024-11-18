package com.domain.usecases

import com.domain.repository.CategoryClientApi
import com.model.CategoryResponseModel

class GetCategoriesUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( ): List<CategoryResponseModel> {

        return client.getCategories ( )
    }
}