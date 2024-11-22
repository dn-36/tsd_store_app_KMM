package com.component.add_products.viewmodel

import com.model.ElementSpecification
import com.model.ProductResponseModel

data class AddProductsState (

    val nameGroup: String = "",

    val totalAmount: String = "",

    val listElementSpecification: List<ElementSpecification> = emptyList(),

    val listSelectedProducts: List<ElementSpecification>? = null,

    val isSet: Boolean = true,

    val byCategory: Float = 1f,

    val indexMainGroup: Int? = null,

    )
