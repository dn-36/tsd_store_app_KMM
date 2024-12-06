package com.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.CategoriesScreenApi
import com.ProductsMenuScreenApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.domain.usecases.GetCategoriesUseCase
import com.model.CategoryResponseModels
import com.domain.usecases.CreateCategoryUseCase
import com.domain.usecases.DeleteCategoryUseCase
import com.domain.usecases.UpdateCategoryUseCase
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class CategoriesViewModel (

    val getCategoriesUseCase: GetCategoriesUseCase,

    val createCategoryUseCase: CreateCategoryUseCase,

    val updateCategoryUseCase: UpdateCategoryUseCase,

    val deleteCategoryUseCase: DeleteCategoryUseCase

    ):NetworkViewModel() {

    var state by mutableStateOf(CategoriesState())

    fun processIntents( intent: CategoriesIntents) {

        when ( intent ) {

            is CategoriesIntents.SetScreen -> {


                intent.coroutineScope.launch (Dispatchers.IO) {

                    if (state.isSet) {

                        val listCategories = getCategoriesUseCase.execute()

                        state = state.copy(

                            listCategories = listCategories,

                            filteredListCategories = listCategories,

                            isSet = false

                        )

                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
                }

            }

            is CategoriesIntents.OpenCreateDataEntry -> openCreateDataEntry()

            is CategoriesIntents.OpenUpdateDataEntry -> openUpdateDataEntry(intent.item)

            is CategoriesIntents.BackFromDataEntry -> backFromDataEntry()

            is CategoriesIntents.BackToProductsMenu -> backToProductsMenu()

            is CategoriesIntents.CreateCategory -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

             intent.coroutineScope.launch (Dispatchers.IO) {

                 createCategoryUseCase.execute(intent.name)

                 val listCategories = getCategoriesUseCase.execute()

                 state = state.copy(

                     isVisibilityDataEntryComponent = false,

                     listCategories = listCategories,

                     filteredListCategories = listCategories,

                 )

                 setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

             }

            }

            is CategoriesIntents.NoDelete -> noDelete()

            is CategoriesIntents.DeleteCategory -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    deleteCategoryUseCase.execute(state.updateItem!!.id?:0)

                    val listCategories = getCategoriesUseCase.execute()

                    state = state.copy(

                        isVisibilityDeleteComponent = false,

                        updateItem = null,

                        listCategories = listCategories,

                        filteredListCategories = listCategories,

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CategoriesIntents.UpdateCategory -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    updateCategoryUseCase.execute( intent.name, state.updateItem!!.id?:0 )

                    val listCategories = getCategoriesUseCase.execute()

                    state = state.copy(

                        isVisibilityDataEntryComponent = false,

                        listCategories = listCategories,

                        filteredListCategories = listCategories,

                        updateItem = null

                        )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CategoriesIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is CategoriesIntents.InputTextSearchComponent -> inputTextSearchComponent(intent.text)

            is CategoriesIntents.LongPressItem -> longPressItem(intent.index)

            is CategoriesIntents.OnePressItem -> onePressItem()

        }

    }

    fun openCreateDataEntry() {

        state = state.copy(

            isVisibilityDataEntryComponent = true

        )

    }

    fun openUpdateDataEntry( item: CategoryResponseModels) {

        state = state.copy(

            isVisibilityDataEntryComponent = true,

            updateItem = item

        )

    }

    fun backFromDataEntry() {

        state = state.copy(

            isVisibilityDataEntryComponent = false,

            updateItem = null

        )

    }

    fun backToProductsMenu() {

        val productsMenuScreen: ProductsMenuScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(productsMenuScreen.productsMenuScreen())

    }

    fun noDelete() {

        state = state.copy(

            isVisibilityDeleteComponent = false,

            updateItem = null

        )

    }

    fun openDeleteComponent( item: CategoryResponseModels) {

        state = state.copy(

            isVisibilityDeleteComponent = true,

            updateItem = item

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listCategories.filter {

            val name = it.name.orEmpty()

            name.contains(text, ignoreCase = true)

        }

    state = state.copy(

        filteredListCategories = newList

    )

        println("Text ${text}")
        println("NewList ${newList}")

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listCategories.size){0F}

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listCategories.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

}