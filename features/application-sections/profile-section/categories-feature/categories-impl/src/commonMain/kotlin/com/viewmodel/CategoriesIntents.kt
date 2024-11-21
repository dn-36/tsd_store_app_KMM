package com.viewmodel

import com.model.CategoryResponseModels
import kotlinx.coroutines.CoroutineScope

sealed class CategoriesIntents {

    data class SetScreen ( val coroutineScope: CoroutineScope ): CategoriesIntents()

    object OpenCreateDataEntry: CategoriesIntents()

    data class OpenUpdateDataEntry( val item: CategoryResponseModels): CategoriesIntents()

    object BackFromDataEntry: CategoriesIntents()

    object BackToProductsMenu: CategoriesIntents()

    object NoDelete: CategoriesIntents()

    data class DeleteCategory( val coroutineScope: CoroutineScope ): CategoriesIntents()

    data class CreateCategory( val coroutineScope: CoroutineScope,

                               val name: String ): CategoriesIntents()

    data class UpdateCategory( val coroutineScope: CoroutineScope,

                               val name: String ): CategoriesIntents()

    data class OpenDeleteComponent( val item: CategoryResponseModels): CategoriesIntents()

    data class InputTextSearchComponent( val text: String ): CategoriesIntents()

    data class LongPressItem( val index: Int ): CategoriesIntents()

    object OnePressItem: CategoriesIntents()

}