package com.arrival_and_consumption_goods.component.create_good_or_service.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.arrival_and_consumption_goods.model.CategoryModel

data class CreateGoodOrServiceState (

    val name: String = "",

    val sku: String = "",

    val price: String = "",

    val category: String = "",

    val descriptionImage: String = "",



    val listFilteredCategory: List<CategoryModel> = emptyList(),


    val expendedCategory: Boolean = false,

    val expendedGoodOrService: Boolean = false,


    val selectedCategory: CategoryModel? = null,

    val selectedGoodOrService: Pair<String,Int> = Pair("Товар",1),


    val image: ImageBitmap? = null,


    val isSet: Boolean = true

)