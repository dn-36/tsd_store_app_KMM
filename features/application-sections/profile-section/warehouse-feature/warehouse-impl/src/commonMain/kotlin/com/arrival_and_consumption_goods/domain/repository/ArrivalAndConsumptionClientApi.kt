package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository

import com.arrival_and_consumption_goods.model.CategoryModel
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

interface ArrivalAndConsumptionClientApi {

    suspend fun getContragents(): List<ContragentResponseArrivalAndConsumption>

    suspend fun getWarehouseArrivalAndConsumption(): List<WarehouseArrivalAndConsumption>

    suspend fun getProducts(): List<AllProductArrivalAndConsumption>

    suspend fun deleteArrivalOrConsumption( ui:String )

    suspend fun createArrivalOrConsumption(

        description: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int ,

        istProducts: List<ProductArrivalAndConsumption>

    )

    suspend fun updateArrivalOrConsumption(

        productUi: String ,

        description: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int ,

        istProducts: List<ProductArrivalAndConsumption>

    )

    suspend fun getArrivalAndConsumption(): List<StoreResponseArrivalAndConsumption>

    suspend fun getCategories(): List<CategoryModel>

    suspend fun createGoodOrService(

        name: String,
        //video_youtube: String,
        //ediz_id: Int?,
        category_id: Int?,
        is_product: Int,
        //is_sale: Int,
        //system_category_id: Int?,
        //is_view_sale: Int,
        //is_order: Int,
        //is_store: Int,
        //is_store_view: Int,
        sku: String,
        text_image: String,
        price: Float?,
        //tags: List<String>,
        //variantes: List<String>,
        //divisions: String,
        image_upload: String?

    )

}