package model

import kotlinx.serialization.Serializable
import product_network.ProductApiClient
import product_network.model.Category
import product_network.model.Connection
import product_network.model.Ediz
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
