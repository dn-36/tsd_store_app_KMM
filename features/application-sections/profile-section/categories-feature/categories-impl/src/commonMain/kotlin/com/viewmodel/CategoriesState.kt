package com.viewmodel

import com.model.CategoryResponseModels

data class CategoriesState(

    val listCategories: List<CategoryResponseModels> = emptyList(),

    val filteredListCategories: List<CategoryResponseModels> = emptyList(),

    val listAlphaTools: List<Float> = emptyList(),

    val isSet: Boolean = true,

    val isVisibilityDataEntryComponent: Boolean = false,

    val isVisibilityDeleteComponent: Boolean = false,

    val updateItem: CategoryResponseModels? = null,

    )
