package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.viewmodel

import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption

data class ListProductsState(

    val filteredProducts: List<AllProductArrivalAndConsumption> = listOf(),

    val nameProductTF: String = "",

    val isSeted: Boolean = true,

    val selectedProduct: ProductArrivalAndConsumption? = null

    )
