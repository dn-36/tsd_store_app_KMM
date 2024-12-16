package com.project.network.characterisrics_products_network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateCharacteristic(

    val name: String,

    val parametrs_id: Int,

    val product_id: Int

)
