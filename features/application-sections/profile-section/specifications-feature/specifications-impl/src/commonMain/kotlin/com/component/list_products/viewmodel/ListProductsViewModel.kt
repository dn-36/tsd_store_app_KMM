package com.component.list_products.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.model.ProductResponseModel

class ListProductsViewModel: ViewModel() {

    var state by mutableStateOf(ListProductsState())

    fun processIntents ( intent: ListProductsIntents) {

        when (intent) {

            is ListProductsIntents.SetScreen -> setScreen(intent.listProducts)

            is ListProductsIntents.InputTextName -> inputText(intent.listProducts,intent.text)

        }

    }

    fun setScreen ( listProducts: List<ProductResponseModel> ) {

     if ( state.isSet ) {

         state = state.copy(

             filteredListProducts = listProducts,

             isSet = false

         )

     }

    }

    fun inputText ( listProducts: List<ProductResponseModel>, text:String ) {

        state = state.copy(

            filteredListProducts = listProducts,

            name = text

        )

    }

}