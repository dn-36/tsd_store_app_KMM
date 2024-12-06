package domain.repository

import model.CategoryGoodsServicesModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

interface GoodsAndServicesClientApi {

    suspend fun getToken(): String

    suspend fun getGoodsAndServices(): List<ProductGoodsServicesModel>

    suspend fun getSystemCategory(): List<SystemCategoryGoodsServicesModel>

    suspend fun getCategory(): List<CategoryGoodsServicesModel>

    suspend fun getUnitsMeasurement(): List<UnitGoodsAndServicesModel>

}