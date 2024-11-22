package com.model

data class ElementSpecification (

    val product: MutableList<ProductResponseModel>,

    val count: MutableList<String>,//Float

    val block: String,

    val price_item: MutableList<String>,//Float

    val totalPrice: MutableList<String>,//Float

    val nds: MutableList<String>,//Int

    val spectext: MutableList<String>

)
