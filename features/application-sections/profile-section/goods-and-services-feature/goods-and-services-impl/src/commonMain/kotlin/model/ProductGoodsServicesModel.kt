package model

import kotlinx.serialization.Serializable
import product_network.ProductApiClient
import product_network.model.Category
import product_network.model.Connection
import product_network.model.Ediz
import product_network.model.LocalStore
import product_network.model.Seo

/*
name*: 'ewgweg'
video_youtube: ссылка на видео из ютуб
ediz_id: id Ед. измерения
category_id: id Категории
is_product: 0/1 ( 0 - услуга, 1 - товар)
is_sale: 0/1 ( 0 - не продается, 1 - продается)
min_count_store: 0 (int минимальный отстаток)
is_only_industry: 0/1 (только производство)
system_category_id: id Системной категории
is_view_sale: 0/1 (отображать на сайте)
is_order: 0/1 (под заказ)
is_store: 0/1 (В наличие)
is_store_view: 0/1 (отображать наличие)
is_test: 0/1 (Можно взять на тест)
is_arenda: 0/1 (Можно взять в аренду)
is_zakaz: 0/1 (Можно заказать)
is_ves: 0/1 (Весовой товар)
is_serial_nomer: 0/1 (Учет по серийному номеру)
is_date_fabrica: 0/1 (Учитывать дату производства)
is_markirovka: 0/1 (Маркированный товар)
is_bu: 0/1 (Б/у или нет)
is_ob_zvonok: 0/1 (обратный звонок по товару)
metka_system: '' (Системная метка)
sku: '' (SKU товара)
text_image: '' (текст изображения)
creater: '' (Производитель)
nomer_creater: '' (Номер произовдителя)
postavka: '' (Срок поставки)
url: '' (slug для ссылки на английском )
price: float (цена)
tags: [] (пока что пустой массив отправлять)
variantes: [] (пока что пустой массив отправлять)
divisions: '' (пока что пустой строкой отправлять)
image_upload: base64
 */

data class ProductGoodsServicesModel(
    val id: Int?, //
    val name: String?, //
    //val text: String?, //
    val price: Float?, //
    val image: String?, //
    val category_id: Int?, //
    //val creater_id: Int?, //
   // val company_id: Int?, //
   // val created_at: String?,
    //val updated_at: String?,
    //val active_pay_id: Int?,
    val ediz_id: Int?, //
    val is_product: Int?,
    val is_sale: Int?, //
    val is_view_sale: Int?,
    //val sale_price: Int?,
    val is_order: Int?,
    val is_store: Int?,
    val is_store_view: Int?,
    val is_ob_zvonok: Int?,
    val is_test: Int?,
    val is_arenda: Int?,
    val is_zakaz: Int?,
    val is_ves: Int?,
    val is_serial_nomer: Int?,
    val is_date_fabrica: Int?,
    val is_markirovka: Int?,
    val metka_system: String?,
    val sku: String?, //
    val text_image: String?,
    val creater: String?,
    val nomer_creater: String?,
    val postavka: String?,
    val url: String?,
    val is_bu: Int?,
    //val ui: String?,
    val is_only_industry: Int?,
    val min_count_store: Int?,  //
    //val video: String?,  //
    val video_youtube: String?, //
    //val video_mobile: String?,  //
    val system_category_id: String?, //
    //val seo: Seo?,
    val ediz: EdizModel?,
    val category: CategoryModel?,  //

)

data class EdizModel(
    val id: Int?,
    val company_id: Int?,
    val name: String?,
    val ui: String?,
    val created_at: String?,
    val updated_at: String?
)

data class CategoryModel(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?
)