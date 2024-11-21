package com.domain.usecases

import com.domain.repository.CategoryClientApi

class DeleteCategoryUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( id: Int ) {

        return client.deleteCategory( id )
    }
}