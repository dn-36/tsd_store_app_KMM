package com.component.add_products.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.model.ElementSpecification

class AddProductsViewModel: ViewModel() {

    var state by mutableStateOf(AddProductsState())

    fun processIntents ( intent: AddProductsIntents ) {

        when ( intent ) {

            is AddProductsIntents.InputTextNameGroup -> inputTextNameGroup(intent.text)

            is AddProductsIntents.InputTextCount -> inputTextCount(

                intent.text, intent.index, intent.indexMain )

            is AddProductsIntents.InputTextPrice -> inputTextPrice(

                intent.text, intent.index, intent.indexMain )

            is AddProductsIntents.InputTextNDS -> inputTextNDS(

                intent.text, intent.index, intent.indexMain )

            is AddProductsIntents.InputTextDescription-> inputTextDescription(

                intent.text, intent.index, intent.indexMain )


            is AddProductsIntents.SetScreen -> setScreen(intent.listSelectedProducts)

        }

    }

    fun inputTextNameGroup ( text: String ) {

        state = state.copy(

            nameGroup = text

        )

    }

    fun setScreen ( listSelectedProducts: List<ElementSpecification> ) {

        if ( state.isSet ) {

            val newList =

                List( if (listSelectedProducts.size != 0) listSelectedProducts.size else 0) { "" }

            state = state.copy(

                listElementSpecification = if ( listSelectedProducts.size != 0 )

                    listSelectedProducts else emptyList(),

                isSet = false

            )

        }

    }

    fun inputTextCount ( text: String, index: Int, indexMain: Int ) {

        val newList = state.listElementSpecification.toMutableList()

        val updatedCount = newList[indexMain].count.toMutableList()

        val updatedTotalPrice = newList[indexMain].totalPrice.toMutableList()

        updatedCount[index] = text

        val countFloat = updatedCount[index].toFloatOrNull()?:0f

        val priceFloat = newList[indexMain].price_item[index].toFloatOrNull()?:0f

        val newTotalPrice = ( countFloat * priceFloat).toString()

        updatedTotalPrice[index] = newTotalPrice

        newList[indexMain] = newList[indexMain].copy(count = updatedCount,

            totalPrice = updatedTotalPrice)

        state = state.copy(
            listElementSpecification = newList
        )


    }

    fun inputTextPrice ( text: String, index: Int, indexMain: Int ) {

        val newList = state.listElementSpecification.toMutableList()

        val updatedPrice = newList[indexMain].price_item.toMutableList()

        val updatedTotalPrice = newList[indexMain].totalPrice.toMutableList()

        updatedPrice[index] = text

        val countFloat = newList[indexMain].count[index].toFloatOrNull()?:0f

        val priceFloat = updatedPrice[index].toFloatOrNull()?:0f

        val newTotalPrice = ( countFloat * priceFloat).toString()

        updatedTotalPrice[index] = newTotalPrice

        newList[indexMain] = newList[indexMain].copy(price_item = updatedPrice,

            totalPrice = updatedTotalPrice)

        state = state.copy(
            listElementSpecification = newList
        )

    }

    fun inputTextNDS ( text: String, index: Int, indexMain: Int ) {

        val newList = state.listElementSpecification.toMutableList()

        // Создаем копию списка nds
        val updatedNDS = newList[indexMain].nds.toMutableList()

        updatedNDS[index] = text // Обновляем нужное значение

        // Обновляем объект в списке
        newList[indexMain] = newList[indexMain].copy(nds = updatedNDS)

        // Обновляем состояние
        state = state.copy(
            listElementSpecification = newList
        )

    }

    fun inputTextDescription ( text: String, index: Int, indexMain: Int ) {

        val newList = state.listElementSpecification.toMutableList()

        val updatedDescription = newList[indexMain].spectext.toMutableList()

        updatedDescription[index] = text

        newList[indexMain] = newList[indexMain].copy(spectext = updatedDescription)

        state = state.copy(

            listElementSpecification = newList
        )

    }

}