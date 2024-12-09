package com.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi

class CreateGoodOrServiceUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute(

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

    ) {

        return client.createGoodOrService (

            name, category_id, is_product, sku, text_image, price, image_upload

        )
    }
}