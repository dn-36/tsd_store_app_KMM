package com.component.list_products.viewmodel

import com.model.ProductResponseModel

data class ListProductsState(

    val filteredListProducts: List<ProductResponseModel> = emptyList(),

    val isSet: Boolean = true,

    val name: String = ""

)
