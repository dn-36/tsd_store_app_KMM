package com.project.network.specifications_network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateSpecification(

    val name: String,
    val text: String?,
    val valuta_id: Int?,
    val local_store_id: Int?,
    val price: Int?,
    val status: Int?,
    val items: List<Items>?

)

@Serializable
data class Items(

    val product_id: Int?,
    val count: Float?,
    val block: String?,
    val spectext: String?,
    val price_item: Float?,
    val price_id: Int?,
    val nds: Int?

)
