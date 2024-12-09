package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption

class ListProductsViewModel {

    var state by mutableStateOf( ListProductsState() )

    fun processIntents (intent : ListProductsIntents) {

        when(intent){

            is ListProductsIntents.SetScreen -> setScreen( intent.listAllProducts )

            is ListProductsIntents.InputText -> inputText( intent.text, intent.listAllProducts )

            is ListProductsIntents.SelectedProduct -> selectedProduct( intent.item )

        }

    }

    fun setScreen ( listAllProducts: List<AllProductArrivalAndConsumption> ) {

        if ( state.isSeted ) {

            state = state.copy(

                filteredProducts = listAllProducts,

                isSeted = false

            )

        }

    }

    fun inputText ( text: String, list: List<AllProductArrivalAndConsumption> ) {

    state = state.copy(

        filteredProducts = list,

        nameProductTF = text

    )

    }

    fun selectedProduct ( item: AllProductArrivalAndConsumption) {

        state = state.copy(

            selectedProduct = ProductArrivalAndConsumption( product = item, count = 0.0 )

        )

    }

}