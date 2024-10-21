package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model

import product_network.model.Product

data class ProductArrivalAndConsumption(
    val product: Product,   // ID товара
    var count: Int // Количество товаров
)
