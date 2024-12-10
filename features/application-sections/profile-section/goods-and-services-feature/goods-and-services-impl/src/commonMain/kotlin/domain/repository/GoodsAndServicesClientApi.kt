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

    suspend fun createGoodOrService(

    name: String,
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
    //is_test: 0/1 (Можно взять на тест)
    //is_arenda: 0/1 (Можно взять в аренду)
    //is_zakaz: 0/1 (Можно заказать)
    //is_ves: 0/1 (Весовой товар)
    //is_serial_nomer: 0/1 (Учет по серийному номеру)
    //is_date_fabrica: 0/1 (Учитывать дату производства)
    //is_markirovka: 0/1 (Маркированный товар)
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

    )

    suspend fun deleteGoodOrService( id: Int )

}