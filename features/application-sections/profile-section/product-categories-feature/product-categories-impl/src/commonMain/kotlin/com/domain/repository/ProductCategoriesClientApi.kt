package com.domain.repository

import com.model.CategoriesResponseModel

interface ProductCategoriesClientApi {

    suspend fun getToken(): String

    suspend fun getCategories(): List<CategoriesResponseModel>

}