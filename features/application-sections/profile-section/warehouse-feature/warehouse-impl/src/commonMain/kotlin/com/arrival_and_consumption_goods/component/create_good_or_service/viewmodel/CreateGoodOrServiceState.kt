package com.arrival_and_consumption_goods.component.create_good_or_service.viewmodel

import androidx.compose.ui.graphics.Color
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

    val expendedForSale: Boolean = false,

    val expendedDisplayOnSite: Boolean = false,

    val expendedUnderOrder: Boolean = false,

    val expendedIsStock: Boolean = false,

    val expendedDisplayStock: Boolean = false,


    val selectedCategory: CategoryModel? = null,

    val selectedGoodOrService: Pair<String,Int> = Pair("Товар",1),

    val selectedForSale: Pair<String,Int> = Pair("Да",1),

    val selectedDisplayOnSite: Pair<String,Int> = Pair("Да",1),

    val selectedUnderOrder: Pair<String,Int> = Pair("нет",0),

    val selectedIsStock: Pair<String,Int> = Pair("да",1),

    val selectedDisplayStock: Pair<String,Int> = Pair("да",1),


    val image: ImageBitmap? = null,


    val isSet: Boolean = true,


    val listColorBorderTF : List <Color> = listOf ( Color.LightGray, Color.LightGray),

    val onCheck: Boolean = false

)