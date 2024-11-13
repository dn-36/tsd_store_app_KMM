package com.component.add_products.viewmodel

import com.model.ElementSpecification

sealed class AddProductsIntents {

    data class InputTextNameGroup ( val text: String ): AddProductsIntents()

    data class InputTextCount (val text: String, val index: Int, val indexMain: Int ): AddProductsIntents()

    data class InputTextPrice (val text: String, val index: Int, val indexMain: Int ): AddProductsIntents()

    data class InputTextNDS (val text: String, val index: Int, val indexMain: Int ): AddProductsIntents()

    data class InputTextDescription (val text: String, val index: Int, val indexMain: Int ): AddProductsIntents()

    data class SetScreen (

        val listSelectedProducts: List<ElementSpecification> ): AddProductsIntents()

}