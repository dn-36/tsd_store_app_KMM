package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable




@Serializable
data class Product(

    val product:ProductInfo

)

@Serializable
data class ProductInfo(

    val id: Int,

    val count: Int

)