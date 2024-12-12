package domain.repository

import model.CategoryGoodsServicesModel
import model.CharacteristicModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

interface GoodsAndServicesClientApi {

    suspend fun getToken(): String

    suspend fun getGoodsAndServices(): List<ProductGoodsServicesModel>

    suspend fun getSystemCategory(): List<SystemCategoryGoodsServicesModel>

    suspend fun getCategory(): List<CategoryGoodsServicesModel>

    suspend fun getUnitsMeasurement(): List<UnitGoodsAndServicesModel>

    suspend fun getCharacteristics(): List<CharacteristicModel>

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

    )

    suspend fun updateGoodOrService(

        id: Int,
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

    )

    suspend fun deleteGoodOrService( id: Int )

}