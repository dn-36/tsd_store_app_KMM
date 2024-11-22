package com.component.list_products.viewmodel

import com.model.ProductResponseModel

sealed class ListProductsIntents {

    data class SetScreen( val listProducts: List<ProductResponseModel> ): ListProductsIntents()

    data class InputTextName( val listProducts: List<ProductResponseModel>,

                              val text:String ): ListProductsIntents()

}