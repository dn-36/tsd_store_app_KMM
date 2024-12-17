package model

import product_network.model.LocalStore
import product_network.model.Seo

data class ProductModel(

    val id: Int?,
    val name: String?,
    val text: String?,
    val price: Float?,
    val sku: String?,
    val ui: String?,

    )
