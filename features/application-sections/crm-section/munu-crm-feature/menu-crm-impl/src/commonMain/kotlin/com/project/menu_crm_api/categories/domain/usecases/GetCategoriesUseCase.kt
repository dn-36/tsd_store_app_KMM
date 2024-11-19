package com.project.`menu-crm-api`.categories.domain.usecases

import com.project.`menu-crm-api`.categories.domain.repository.CategoryClientApi
import com.project.`menu-crm-api`.categories.model.CategoryResponseModel

class GetCategoriesUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( ): List<CategoryResponseModel> {

        return client.getCategories ( )
    }
}