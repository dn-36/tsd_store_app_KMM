package com.project.`menu-crm-api`.categories.model

data class CategoryResponseModel(
    val id: Int,
    val name: String,
    val creater_id: Int,
    val company_id: Int,
    val created_at: String,
    val updated_at: String,
    val url: String,
    val ui: String,
    val category_langs: List<CategoryLangModel>
)

data class CategoryLangModel(
    val id: Int,
    val name: String,
    val lang_id: Int,
    val created_at: String,
    val updated_at: String,
    val category_id: Int
)
