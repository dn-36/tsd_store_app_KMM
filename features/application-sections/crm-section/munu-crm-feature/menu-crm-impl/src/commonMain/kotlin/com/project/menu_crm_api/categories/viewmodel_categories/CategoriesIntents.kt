package com.project.menu_crm_api.categories.viewmodel_categories

import com.project.`menu-crm-api`.categories.model.CategoryResponseModel
import kotlinx.coroutines.CoroutineScope

sealed class CategoriesIntents {

    data class SetScreen ( val coroutineScope: CoroutineScope ): CategoriesIntents()

    object OpenCreateDataEntry: CategoriesIntents()

    data class OpenUpdateDataEntry( val item: CategoryResponseModel ): CategoriesIntents()

    object BackFromDataEntry: CategoriesIntents()

    object NoDelete: CategoriesIntents()

    data class DeleteCategory( val coroutineScope: CoroutineScope ): CategoriesIntents()

    data class CreateCategory( val coroutineScope: CoroutineScope,

                               val name: String ): CategoriesIntents()

    data class UpdateCategory( val coroutineScope: CoroutineScope,

                               val name: String ): CategoriesIntents()

    data class OpenDeleteComponent( val item: CategoryResponseModel ): CategoriesIntents()

    data class InputTextSearchComponent( val text: String ): CategoriesIntents()

}