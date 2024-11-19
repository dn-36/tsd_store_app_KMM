package com.project.`menu-crm-api`.categories.domain.repository

import com.project.`menu-crm-api`.categories.model.CategoryResponseModel

interface CategoryClientApi {

    suspend fun getToken(): String

    suspend fun getCategories(): List<CategoryResponseModel>

    suspend fun createCategory( name: String )

    suspend fun updateCategory( name: String, id:Int )

    suspend fun deleteCategory( id: Int )

}