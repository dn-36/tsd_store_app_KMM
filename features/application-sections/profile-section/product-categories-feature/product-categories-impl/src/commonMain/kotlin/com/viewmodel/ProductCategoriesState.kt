package com.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.model.CategoriesResponseModel

data class ProductCategoriesState(

    val isSet: Boolean = true,

    val listCategories: List<CategoriesResponseModel> = emptyList(),

    val image: ImageBitmap? = null,

    )
