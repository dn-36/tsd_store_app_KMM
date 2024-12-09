package domain.usecases

import domain.repository.GoodsAndServicesClientApi
import model.ProductGoodsServicesModel

class CreateGoodOrServiceUseCase (

    private val client: GoodsAndServicesClientApi,

    ) {

    suspend fun execute(name: String,
                        video_youtube: String,
                        ediz_id: Int?,
                        category_id: Int?,
                        is_product: Int,
                        is_sale: Int,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
                        system_category_id: Int?,
                        is_view_sale: Int,
                        is_order: Int,
                        is_store: Int,
                        is_store_view: Int,
        //is_test: 0/1 (Можно взять на тест)
        //is_arenda: 0/1 (Можно взять в аренду)
        //is_zakaz: 0/1 (Можно заказать)
        //is_ves: 0/1 (Весовой товар)
        //is_serial_nomer: 0/1 (Учет по серийному номеру)
        //is_date_fabrica: 0/1 (Учитывать дату производства)
        //is_markirovka: 0/1 (Маркированный товар)
        //is_bu: 0/1 (Б/у или нет)
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
                        sku: String,
                        text_image: String,
        //creater: '' (Производитель)
        //nomer_creater: '' (Номер произовдителя)
        //postavka: '' (Срок поставки)
        //url: '' (slug для ссылки на английском )
                        price: Float?,
                        tags: List<String>, //(пока что пустой массив отправлять)
                        variantes: List<String>, //(пока что пустой массив отправлять)
                        divisions: String, //(пока что пустой строкой отправлять)
                        image_upload: String?

    ) {

        client.createGoodOrService( name, video_youtube, ediz_id, category_id, is_product, is_sale,

            system_category_id, is_view_sale, is_order, is_store, is_store_view, sku, text_image,

            price, tags, variantes, divisions, image_upload)
    }
}