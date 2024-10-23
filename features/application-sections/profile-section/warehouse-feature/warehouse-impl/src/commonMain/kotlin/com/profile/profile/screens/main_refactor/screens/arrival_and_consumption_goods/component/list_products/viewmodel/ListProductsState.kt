package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.viewmodel

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

data class ListProductsState(

    val filteredWarehouse: List<AllProductArrivalAndConsumption> = listOf(),

    val nameProductTF: String = "",

    val isSeted: Boolean = true,

    val selectedProduct: ProductArrivalAndConsumption? = null

    )
