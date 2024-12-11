package com.arrival_and_consumption_goods.component.create_good_or_service.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arrival_and_consumption_goods.domain.usecases.GetCategoriesUseCase
import com.arrival_and_consumption_goods.model.CategoryModel
import com.project.core_app.network_base_screen.NetworkViewModel

class CreateGoodOrServiceViewModel(): ViewModel() {

    var state by mutableStateOf(CreateGoodOrServiceState())

    fun processIntents( intent: CreateGoodOrServiceIntents ) {

        when ( intent ) {

            is CreateGoodOrServiceIntents.SetScreen -> setScreen( intent.lisCategory,

                 intent.sku )

            is CreateGoodOrServiceIntents.InputTextName -> inputTextName(intent.text)

            is CreateGoodOrServiceIntents.InputTextSku -> inputTextSku(intent.text)

            is CreateGoodOrServiceIntents.InputTextDescriptionImage -> {

                inputTextDescriptionImage(intent.text)

            }

            is CreateGoodOrServiceIntents.InputTextPrice -> inputTextPrice(intent.text)

            is CreateGoodOrServiceIntents.InputTextCategory -> {

                inputTextCategory(intent.text, intent.list)

            }



            is CreateGoodOrServiceIntents.MenuCategory -> menuCategory()

            is CreateGoodOrServiceIntents.MenuGoodOrService -> menuGoodOrService()

            is CreateGoodOrServiceIntents.MenuForSale -> menuForSale()

            is CreateGoodOrServiceIntents.MenuDisplayOnSite -> menuDisplayOnSite()

            is CreateGoodOrServiceIntents.MenuUnderOrder -> menuUnderOrder()

            is CreateGoodOrServiceIntents.MenuIsStock -> menuIsStock()

            is CreateGoodOrServiceIntents.MenuDisplayStock -> menuDisplayStock()



            is CreateGoodOrServiceIntents.SelectCategory -> selectCategory(intent.item)

            is CreateGoodOrServiceIntents.SelectDisplayOnSite -> selectDisplayOnSite(intent.index)

            is CreateGoodOrServiceIntents.SelectUnderOrder -> selectUnderOrder(intent.index)

            is CreateGoodOrServiceIntents.SelectIsStock -> selectIsStock(intent.index)

            is CreateGoodOrServiceIntents.SelectDisplayStock -> selectDisplayStock(intent.index)

            is CreateGoodOrServiceIntents.SelectGoodOrService -> {

                selectGoodOrService(intent.index)

            }

            is CreateGoodOrServiceIntents.SelectForSale -> {

                selectForSale(intent.index)

            }


            is CreateGoodOrServiceIntents.DeleteSelectedCategory -> deleteSelectedCategory()

            is CreateGoodOrServiceIntents.DeleteSelectedUnderOrder -> deleteSelectedUnderOrder()

            is CreateGoodOrServiceIntents.DeleteSelectedIsStock -> deleteSelectedIsStock()

            is CreateGoodOrServiceIntents.DeleteSelectedDisplayStock -> deleteSelectedDisplayStock()

            is CreateGoodOrServiceIntents.DeleteSelectedDisplayOnSite -> {

                deleteSelectedDisplayOnSite()

            }

            is CreateGoodOrServiceIntents.DeleteSelectedGoodOrService -> {

                deleteSelectedGoodOrService()

            }

            is CreateGoodOrServiceIntents.DeleteSelectedForSale -> deleteSelectedForSale()

            is CreateGoodOrServiceIntents.DeleteSelectedPhoto -> deleteSelectedPhoto()

        }

    }


    fun setScreen( listCategory: List<CategoryModel>,

                   sku: String ){

        if ( state.isSet ) {

            state = state.copy(

                listFilteredCategory = listCategory,

                sku = sku,

                isSet = false

            )
        }

    }

    fun inputTextName( text: String ) {

        state = state.copy(

            name = text

        )

    }

    fun inputTextSku( text: String ) {

        state = state.copy(

            sku = text

        )

    }

    fun inputTextPrice( text: String ) {

        state = state.copy(

            price = text

        )

    }

    fun inputTextDescriptionImage( text: String ) {

        state = state.copy(

            descriptionImage = text

        )

    }

    fun inputTextCategory( text: String, list: List<CategoryModel> ) {

        state = state.copy(

            category = text,

            listFilteredCategory = list

        )

    }



    fun menuCategory(){

        state = state.copy(

            expendedCategory = !state.expendedCategory,

            expendedGoodOrService = false

        )

    }

    fun menuGoodOrService(){

        state = state.copy(

            expendedGoodOrService = !state.expendedGoodOrService,

            expendedCategory = false,

            )

    }

    fun menuForSale(){

        state = state.copy(

            expendedForSale = !state.expendedForSale,

            expendedCategory = false,

            )

    }

    fun menuDisplayOnSite(){

        state = state.copy(

            expendedDisplayOnSite = !state.expendedDisplayOnSite,

            expendedCategory = false,

            )

    }

    fun menuUnderOrder(){

        state = state.copy(

            expendedUnderOrder = !state.expendedUnderOrder,

            expendedCategory = false,

            )

    }

    fun menuIsStock(){

        state = state.copy(

            expendedIsStock = !state.expendedIsStock,

            expendedCategory = false,

            )

    }

    fun menuDisplayStock(){

        state = state.copy(

            expendedDisplayStock = !state.expendedDisplayStock,

            expendedCategory = false,

            )

    }



    fun selectCategory( item: CategoryModel){

        state = state.copy(

            selectedCategory = item,

            expendedCategory = false

        )

    }

    fun selectGoodOrService( index: Int ) {

        state = state.copy(

            selectedGoodOrService =  if ( index == 1 ) Pair("Товар",1) else Pair("Услуга",0),

            expendedGoodOrService = false

        )

    }

    fun selectForSale( index: Int ) {

        state = state.copy(

            selectedForSale =  if ( index == 1 ) Pair("Да",1) else Pair("Нет",0),

            expendedForSale = false

        )

    }

    fun selectDisplayOnSite( index: Int ) {

        state = state.copy(

            selectedForSale =  if ( index == 1 ) Pair("Да",1) else Pair("Нет",0),

            expendedForSale = false

        )

    }


    fun selectIsStock( index: Int ){

        state = state.copy(

            selectedIsStock = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsStock = false

        )

    }

    fun selectDisplayStock( index: Int ){

        state = state.copy(

            selectedDisplayStock = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedDisplayStock = false

        )

    }

    fun selectUnderOrder( index: Int ){

        state = state.copy(

            selectedUnderOrder = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedUnderOrder = false

        )

    }




    fun deleteSelectedCategory(){

        state = state.copy(

            selectedCategory = null

        )

    }




    fun deleteSelectedGoodOrService(){

        state = state.copy(

            selectedGoodOrService = Pair("Товар",1)

        )

    }

    fun deleteSelectedForSale(){

        state = state.copy(

            selectedForSale = Pair("Да",1)

        )

    }

    fun deleteSelectedDisplayOnSite(){

        state = state.copy(

            selectedForSale = Pair("Да",1)

        )

    }


    fun deleteSelectedUnderOrder(){

        state = state.copy(

            selectedUnderOrder = Pair("Нет",0)

        )

    }

    fun deleteSelectedIsStock(){

        state = state.copy(

            selectedIsStock = Pair("Да",1)

        )

    }

    fun deleteSelectedDisplayStock(){

        state = state.copy(

            selectedDisplayStock = Pair("Да",1)

        )

    }

    fun deleteSelectedPhoto(){

        state = state.copy(

            image = null

        )

    }

}