package viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import model.CategoryGoodsServicesModel
import model.CharacteristicModel
import model.ParameterModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

data class GoodsAndServicesState(

    val isSet: Boolean = true,

    val listProducts: List<ProductGoodsServicesModel> = emptyList(),

    val listFilteredProducts: List<ProductGoodsServicesModel> = emptyList(),

    val listSystemCategory: List<SystemCategoryGoodsServicesModel> = emptyList(),

    val listCategory: List<CategoryGoodsServicesModel> = emptyList(),

    val listUnitsMeasurement: List<UnitGoodsAndServicesModel> = emptyList(),

    val listCharacteristics: List<CharacteristicModel> = emptyList(),

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
    val is_test: Int? = null,
    val is_arenda: Int? = null,
    val is_zakaz: Int? = null,
    val is_ves: Int? = null,
    val is_serial_nomer: Int? = null,
    val is_date_fabrica: Int? = null,
    val is_markirovka: Int? = null,
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
