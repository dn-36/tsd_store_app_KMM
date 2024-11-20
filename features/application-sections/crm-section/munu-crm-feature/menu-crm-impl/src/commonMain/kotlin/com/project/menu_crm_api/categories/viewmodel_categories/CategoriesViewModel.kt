package com.project.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.categories.domain.usecases.GetCategoriesUseCase
import com.project.`menu-crm-api`.categories.model.CategoryResponseModel
import com.project.menu_crm_api.categories.domain.usecases.CreateCategoryUseCase
import com.project.menu_crm_api.categories.domain.usecases.DeleteCategoryUseCase
import com.project.menu_crm_api.categories.domain.usecases.UpdateCategoryUseCase
import com.project.menu_crm_api.categories.viewmodel_categories.CategoriesIntents
import com.project.menu_crm_api.categories.viewmodel_categories.CategoriesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

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

                        state = state.copy(

                            listCategories = getCategoriesUseCase.execute(),

                            filteredListCategories = getCategoriesUseCase.execute(),

                            isSet = false

                        )

                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
                }

            }

            is CategoriesIntents.OpenCreateDataEntry -> openCreateDataEntry()

            is CategoriesIntents.OpenUpdateDataEntry -> openUpdateDataEntry(intent.item)

            is CategoriesIntents.BackFromDataEntry -> backFromDataEntry()

            is CategoriesIntents.CreateCategory -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

             intent.coroutineScope.launch (Dispatchers.IO) {

                 createCategoryUseCase.execute(intent.name)

                 state = state.copy(

                     isVisibilityDataEntryComponent = false,

                     listCategories = getCategoriesUseCase.execute(),

                     filteredListCategories = getCategoriesUseCase.execute(),

                 )

                 setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

             }

            }

            is CategoriesIntents.NoDelete -> noDelete()

            is CategoriesIntents.DeleteCategory -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    deleteCategoryUseCase.execute(state.updateItem!!.id)

                    state = state.copy(

                        isVisibilityDeleteComponent = false,

                        updateItem = null,

                        listCategories = getCategoriesUseCase.execute(),

                        filteredListCategories = getCategoriesUseCase.execute(),

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CategoriesIntents.UpdateCategory -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    updateCategoryUseCase.execute( intent.name, state.updateItem!!.id )

                    state = state.copy(

                        isVisibilityDataEntryComponent = false,

                        listCategories = getCategoriesUseCase.execute(),

                        filteredListCategories = getCategoriesUseCase.execute(),

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

    fun openUpdateDataEntry( item: CategoryResponseModel ) {

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

    fun noDelete() {

        state = state.copy(

            isVisibilityDeleteComponent = false,

            updateItem = null

        )

    }

    fun openDeleteComponent( item: CategoryResponseModel ) {

        state = state.copy(

            isVisibilityDeleteComponent = true,

            updateItem = item

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listCategories.filter {

            it.name.contains(text, ignoreCase = true)

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