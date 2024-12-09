package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.viewmodel

import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

sealed class ListProductsIntents {

    data class SetScreen ( val listAllProducts: List<AllProductArrivalAndConsumption> ): ListProductsIntents()

    data class InputText ( val text: String, val listAllProducts: List<AllProductArrivalAndConsumption> ): ListProductsIntents()

    data class SelectedProduct ( val item: AllProductArrivalAndConsumption): ListProductsIntents()

}