package viewmodel

import androidx.compose.ui.graphics.ImageBitmap
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

    val isVisibilityDataEntry: Boolean = false,

    val listAlphaTools: List<Float> = emptyList(),

    val isVisibilityDeleteComponent: Boolean = false,

    val isVisibilityAdditionalInformationComponent: Boolean = false,

    val isVisibilityDischargeComponent: Boolean = false,

    val updateItem: ProductGoodsServicesModel? = null,

    val manufacturer: String = "",

    val numberManufacturer: String = "",

    val isBu: Int = 0,

    val name: String = "",

    val video_youtube: String = "",

    val ediz_id: Int? = null,

    val category_id: Int? = null,

    val is_product: Int? = null,

    val is_sale: Int? = null,
    //min_count_store: 0 (int минимальный отстаток)
    //is_only_industry: 0/1 (только производство)
    val system_category_id: Int? = null,
    val is_view_sale: Int? = null,
    val is_order: Int? = null,
    val is_store: Int? = null,
    val is_store_view: Int? = null,
    //is_test: 0/1 (Можно взять на тест)
    //is_arenda: 0/1 (Можно взять в аренду)
    //is_zakaz: 0/1 (Можно заказать)
    //is_ves: 0/1 (Весовой товар)
    //is_serial_nomer: 0/1 (Учет по серийному номеру)
    //is_date_fabrica: 0/1 (Учитывать дату производства)
    //is_markirovka: 0/1 (Маркированный товар)
    //is_ob_zvonok: 0/1 (обратный звонок по товару)
    //metka_system: '' (Системная метка)
    val sku: String = "",
    val text_image: String = "",
    val postavka: String = "",
    //url: '' (slug для ссылки на английском )
    val price: Float? = null,
    val tags: List<String> = emptyList(), //(пока что пустой массив отправлять)
    val variantes: List<String> = emptyList(), //(пока что пустой массив отправлять)
    val divisions: String = "", //(пока что пустой строкой отправлять)
    val image_upload: ImageBitmap? = null

    )
