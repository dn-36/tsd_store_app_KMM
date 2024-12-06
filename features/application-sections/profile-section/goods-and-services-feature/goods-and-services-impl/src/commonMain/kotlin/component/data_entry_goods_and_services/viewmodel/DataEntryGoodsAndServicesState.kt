package component.data_entry_goods_and_services.viewmodel

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

    val forSale: String = "да",

    val displayOnSite: String = "да",

    val underOrder: String = "нет",

    val stock: String = "да",

    val displayStock: String = "да",



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



    val selectedSystemCategory: SystemCategoryGoodsServicesModel? = null,

    val selectedCategory: CategoryGoodsServicesModel? = null,

    val selectedUnit: UnitGoodsAndServicesModel? = null,

    val selectedForSale: Int = 1,

    val selectedDisplayOnSite: Int = 1,

    val selectedUnderOrder: Int = 0,

    val selectedIsStock: Int = 1,

    val selectedDisplayStock: Int = 1,


    val isSet: Boolean = true

    )
