package viewmodel

import model.CategoryGoodsServicesModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

data class GoodsAndServicesState(

    val isSet: Boolean = true,

    val listProducts: List<ProductGoodsServicesModel> = emptyList(),

    val listSystemCategory: List<SystemCategoryGoodsServicesModel> = emptyList(),

    val listCategory: List<CategoryGoodsServicesModel> = emptyList(),

    val listUnitsMeasurement: List<UnitGoodsAndServicesModel> = emptyList(),

    val isVisibilityDataEntry: Boolean = false

)
