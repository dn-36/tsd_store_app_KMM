package com.domain.repository

import com.model.CategoryResponseModel

interface CategoryClientApi {

    suspend fun getToken(): String

    suspend fun getCategories(): List<CategoryResponseModel>

}