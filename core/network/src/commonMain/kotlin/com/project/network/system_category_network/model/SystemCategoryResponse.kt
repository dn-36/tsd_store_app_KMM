package com.project.network.system_category_network.model

import kotlinx.serialization.Serializable

@Serializable
data class SystemCategoryResponse(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val created_at: String,
    val updated_at: String,
    val parametrs: List<Parameter> = emptyList()
)

@Serializable
data class Parameter(
    val id: Int?,
    val name: String?,
    val created_at: String,
    val updated_at: String,
    val laravel_through_key: Int?
)
