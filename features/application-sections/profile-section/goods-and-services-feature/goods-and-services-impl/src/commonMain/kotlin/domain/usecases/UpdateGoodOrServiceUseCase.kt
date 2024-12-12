package domain.usecases

import domain.repository.GoodsAndServicesClientApi

class UpdateGoodOrServiceUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute( id: Int, name: String,
                        video_youtube: String,
                        ediz_id: Int?,
                        category_id: Int?,
                        is_product: Int?,
                        is_sale: Int?,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
                        system_category_id: Int?,
                        is_view_sale: Int?,
                        is_order: Int?,
                        is_store: Int?,
                        is_store_view: Int?,
                         is_test: Int?,
                         is_arenda: Int?,
                         is_zakaz: Int?,
                         is_ves: Int?,
                         is_serial_nomer: Int?,
                         is_date_fabrica: Int?,
                         is_markirovka: Int?,
                        is_bu: Int,
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
                        sku: String,
                        text_image: String,
                        creater: String,
                        nomer_creater: String,
                        postavka: String,
        //url: '' (slug для ссылки на английском )
                        price: Float?,
                        tags: List<String>, //(пока что пустой массив отправлять)
                        variantes: List<String>, //(пока что пустой массив отправлять)
                        divisions: String, //(пока что пустой строкой отправлять)
                        image_upload: String?

    ) {

        client.updateGoodOrService( id, name, video_youtube, ediz_id, category_id, is_product,

            is_sale, system_category_id, is_view_sale, is_order, is_store, is_store_view,

            is_test, is_arenda,is_zakaz, is_ves, is_serial_nomer,is_date_fabrica, is_markirovka,

            is_bu, sku, text_image, creater, nomer_creater, postavka, price, tags, variantes,

            divisions, image_upload )
    }
}