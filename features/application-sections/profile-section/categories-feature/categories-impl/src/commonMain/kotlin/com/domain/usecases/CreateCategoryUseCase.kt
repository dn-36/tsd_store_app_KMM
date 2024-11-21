package com.domain.usecases

import com.domain.repository.CategoryClientApi

class CreateCategoryUseCase (

    private val client: CategoryClientApi,

    ) {

    suspend fun execute ( name: String ) {

        return client.createCategory(name)
    }
}