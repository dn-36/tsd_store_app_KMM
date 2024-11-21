package com.domain.usecases

import com.domain.repository.CategoryClientApi

class UpdateCategoryUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( name: String, id: Int ) {

        return client.updateCategory( name, id )
    }
}