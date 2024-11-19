package com.project.menu_crm_api.categories.domain.usecases

import com.project.`menu-crm-api`.categories.domain.repository.CategoryClientApi

class DeleteCategoryUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( id: Int ) {

        return client.deleteCategory( id )
    }
}