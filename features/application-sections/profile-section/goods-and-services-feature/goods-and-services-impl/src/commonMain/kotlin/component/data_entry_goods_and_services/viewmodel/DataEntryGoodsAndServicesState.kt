package component.data_entry_goods_and_services.viewmodel

import androidx.compose.ui.graphics.Color
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

    val unitMeasurement: String = "",

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

    val expendedIsTest: Boolean = false,

    val expendedIsArenda: Boolean = false,

    val expendedIsZakaz: Boolean = false,

    val expendedIsVes: Boolean = false,

    val expendedIsSerialNomer: Boolean = false,

    val expendedDateFabrica: Boolean = false,

    val expendedMarkirovka: Boolean = false,



    val selectedSystemCategory: SystemCategoryGoodsServicesModel? = null,

    val selectedCategory: CategoryGoodsServicesModel? = null,

    val selectedUnit: UnitGoodsAndServicesModel? = null,

    val selectedForSale: Pair<String,Int> = Pair("да",1),

    val selectedDisplayOnSite: Pair<String,Int> = Pair("да",1),

    val selectedUnderOrder: Pair<String,Int> = Pair("нет",0),

    val selectedIsStock: Pair<String,Int> = Pair("да",1),

    val selectedDisplayStock: Pair<String,Int> = Pair("да",1),

    val selectedGoodOrService: Pair<String,Int> = Pair("Товар",1),

    val selectedIsTest: Pair<String,Int> = Pair("да",1),

    val selectedIsArenda: Pair<String,Int> = Pair("да",1),

    val selectedIsZakaz: Pair<String,Int> = Pair("да",1),

    val selectedIsVes: Pair<String,Int> = Pair("нет",0),

    val selectedIsSerialNomer: Pair<String,Int> = Pair("Нет",0),

    val selectedDateFabrica: Pair<String,Int> = Pair("Нет",0),

    val selectedMarkirovka: Pair<String,Int> = Pair("Нет",0),


    val image: ImageBitmap? = null,


    val isSet: Boolean = true,

    val listColorBorderTF : List <Color> = listOf ( Color.LightGray, Color.LightGray),

    val onCheck: Boolean = false,


    val viewingPhoto: Boolean = false

    )
