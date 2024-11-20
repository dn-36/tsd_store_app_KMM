package com.project.menu_crm_api.categories.viewmodel_categories

import com.project.`menu-crm-api`.categories.model.CategoryResponseModel

data class CategoriesState(

    val listCategories: List<CategoryResponseModel> = emptyList(),

    val filteredListCategories: List<CategoryResponseModel> = emptyList(),

    val listAlphaTools: List<Float> = emptyList(),

    val isSet: Boolean = true,

    val isVisibilityDataEntryComponent: Boolean = false,

    val isVisibilityDeleteComponent: Boolean = false,

    val updateItem: CategoryResponseModel? = null,

)
