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


            is AddProductsIntents.SetScreen -> setScreen(intent.listSelectedProducts,

                intent.indexMainGroup, intent.byCategory, intent.totalAmount)

            is AddProductsIntents.ByCategory -> byCategory()

            is AddProductsIntents.ChooseMainGroup -> chooseMainGroup(intent.indexMainGroup)

            is AddProductsIntents.AddGroup -> addGroup()

        }

    }

    fun inputTextNameGroup ( text: String ) {

        state = state.copy(

            nameGroup = text

        )

    }

    fun setScreen ( listSelectedProducts: List<ElementSpecification>, indexMainGroup: Int?,

                    byCategory: Float, totalAmount:String) {

        if ( state.isSet ) {

            state = state.copy(

                listElementSpecification = if ( listSelectedProducts.size != 0 )

                    listSelectedProducts else emptyList(),

                indexMainGroup = indexMainGroup,

                byCategory = byCategory,

                totalAmount = totalAmount,

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

        var newTotalAmount = 0f

        for (item in newList) {
            for (totalPriceString in item.totalPrice) {
                newTotalAmount += totalPriceString.toFloatOrNull() ?: 0f
            }
        }


        state = state.copy(

            listElementSpecification = newList,

            totalAmount = newTotalAmount.toString()
        )

        //println("NEWTOTALAMOUNT State: ${state.totalAmount.toFloatOrNull()}")

        //println("NEWRORALAMOUNT COUNT: ${state.totalAmount}")

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

        var newTotalAmount = 0f

        for (item in newList) {
            for (totalPriceString in item.totalPrice) {
                newTotalAmount += totalPriceString.toFloatOrNull() ?: 0f
            }
        }

        state = state.copy(

            listElementSpecification = newList,

            totalAmount = newTotalAmount.toString()
        )

       // println("NEWTOTALAMOUNT State: ${state.totalAmount.toFloatOrNull()}")

       // println("NEWRORALAMOUNT PRICE: ${state.totalAmount}")

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

    fun byCategory () {

        state = state.copy(

            byCategory = if ( state.byCategory == 1f ) 0f else 1f

        )

    }

    fun chooseMainGroup ( index: Int ) {

    state = state.copy(

        indexMainGroup = index,

        byCategory = 0f

    )

    }

    fun addGroup () {

        val newList = state.listElementSpecification.toMutableList()

        newList.add( ElementSpecification(

            product = mutableListOf(),
            count = mutableListOf(""),
            block = if (state.nameGroup.isNotBlank()) state.nameGroup

            else "Новая группа ${newList.size - 1}",

            price_item = mutableListOf(""),
            nds = mutableListOf(""),
            spectext = mutableListOf(""),
            totalPrice = mutableListOf("")

        ) )

        state = state.copy(

            listElementSpecification = newList,

            indexMainGroup = newList.size - 1,

            byCategory = 0f

        )

    }

}