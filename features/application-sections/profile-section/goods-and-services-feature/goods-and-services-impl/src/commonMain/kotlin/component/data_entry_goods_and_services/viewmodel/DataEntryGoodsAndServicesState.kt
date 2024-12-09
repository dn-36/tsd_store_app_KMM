package component.data_entry_goods_and_services.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import model.CategoryGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

data class DataEntryGoodsAndServicesState (

    val name: String = "",

    val sku: String = "",

    val productLink: String = "",

    val videoYouTube: String = "",

    val price: String = "",

    val category: String = "",

    val systemCategory: String = "",

    val uniMeasurement: String = "",

    val descriptionImage: String = "",



    val listFilteredCategory: List<CategoryGoodsServicesModel> = emptyList(),

    val listFilteredSystemCategory: List<SystemCategoryGoodsServicesModel> = emptyList(),

    val listFilteredUnits: List<UnitGoodsAndServicesModel> = emptyList(),



    val expendedCategory: Boolean = false,

    val expendedSystemCategory: Boolean = false,

    val expendedUnits: Boolean = false,

    val expendedForSale: Boolean = false,

    val expendedDisplayOnSite: Boolean = false,

    val expendedUnderOrder: Boolean = false,

    val expendedIsStock: Boolean = false,

    val expendedDisplayStock: Boolean = false,

    val expendedGoodOrService: Boolean = false,



    val selectedSystemCategory: SystemCategoryGoodsServicesModel? = null,

    val selectedCategory: CategoryGoodsServicesModel? = null,

    val selectedUnit: UnitGoodsAndServicesModel? = null,

    val selectedForSale: Pair<String,Int> = Pair("да",1),

    val selectedDisplayOnSite: Pair<String,Int> = Pair("да",1),

    val selectedUnderOrder: Pair<String,Int> = Pair("нет",0),

    val selectedIsStock: Pair<String,Int> = Pair("да",1),

    val selectedDisplayStock: Pair<String,Int> = Pair("да",1),

    val selectedGoodOrService: Pair<String,Int> = Pair("Товар",1),


    val image: ImageBitmap? = null,


    val isSet: Boolean = true

    )
