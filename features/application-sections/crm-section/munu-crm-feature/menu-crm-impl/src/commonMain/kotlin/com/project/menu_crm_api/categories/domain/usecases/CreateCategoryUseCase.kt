package com.project.menu_crm_api.categories.domain.usecases

import com.project.`menu-crm-api`.categories.domain.repository.CategoryClientApi
import com.project.`menu-crm-api`.categories.model.CategoryResponseModel

class CreateCategoryUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( name: String ) {

        return client.createCategory(name)
    }
}