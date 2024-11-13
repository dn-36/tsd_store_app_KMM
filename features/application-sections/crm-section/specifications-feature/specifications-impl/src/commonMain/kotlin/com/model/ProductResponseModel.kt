package com.model



data class ProductResponseModel(

    val id: Int?,

    val sku: String?,

    val name: String?,

    val ui: String?,

    val price: Float?,

    val category: CategoryProductModel?,

    )

data class CategoryProductModel(

    val id: Int?,

    val name: String?,

    val creater_id: Int?,

    val company_id: Int?,

)
