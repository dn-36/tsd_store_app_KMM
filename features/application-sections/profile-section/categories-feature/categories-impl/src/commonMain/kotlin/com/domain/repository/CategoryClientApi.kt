package com.domain.repository

import com.model.CategoryResponseModels

interface CategoryClientApi {

    suspend fun getToken(): String

    suspend fun getCategories(): List<CategoryResponseModels>

    suspend fun createCategory( name: String )

    suspend fun updateCategory( name: String, id:Int )

    suspend fun deleteCategory( id: Int )

}