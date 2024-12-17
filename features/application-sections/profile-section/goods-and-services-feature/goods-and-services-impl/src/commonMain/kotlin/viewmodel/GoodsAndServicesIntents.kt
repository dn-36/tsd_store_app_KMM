package viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import model.ProductGoodsServicesModel

sealed class GoodsAndServicesIntents {

   object Back: GoodsAndServicesIntents()

   data class SetScreen( val coroutineScope: CoroutineScope ): GoodsAndServicesIntents()

   data class OpenCreateDataEntry ( val coroutineScope: CoroutineScope ): GoodsAndServicesIntents()

   data class OpenUpdateDataEntry ( val coroutineScope: CoroutineScope,

                                    val item: ProductGoodsServicesModel

   ): GoodsAndServicesIntents()

   data class CreateGoodOrService ( val coroutineScope: CoroutineScope

   ): GoodsAndServicesIntents()

    data class UpdateGoodOrService ( val coroutineScope: CoroutineScope

    ): GoodsAndServicesIntents()

   object BackFromDataEntry: GoodsAndServicesIntents()

   object BackFromDischarge: GoodsAndServicesIntents()

   object BackFromAdditionalInformation: GoodsAndServicesIntents()

   object BackFromCharacteristics: GoodsAndServicesIntents()

    data class LongPressItem( val index: Int ): GoodsAndServicesIntents()

    object OnePressItem: GoodsAndServicesIntents()

    data class OpenDeleteComponent ( val item: ProductGoodsServicesModel

    ): GoodsAndServicesIntents()

    object NoDelete: GoodsAndServicesIntents()

    data class Next ( val name: String,
                      val video_youtube: String,
                      val ediz_id: Int?,
                      val category_id: Int?,
                      val is_product: Int,
                      val is_sale: Int,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
                      val system_category_id: Int?,
                      val is_view_sale: Int,
                      val is_order: Int,
                      val is_store: Int,
                      val is_store_view: Int,
                      val is_test: Int?,
                      val is_arenda: Int?,
                      val is_zakaz: Int?,
                      val is_ves: Int?,
                      val is_serial_nomer: Int?,
                      val is_date_fabrica: Int?,
                      val is_markirovka: Int?,
        //is_bu: 0/1 (Б/у или нет)
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
                      val sku: String,
                      val text_image: String,
        //creater: '' (Производитель)
        //nomer_creater: '' (Номер произовдителя)
        //postavka: '' (Срок поставки)
        //url: '' (slug для ссылки на английском )
                      val price: Float?,
                      val tags: List<String>, //(пока что пустой массив отправлять)
                      val variantes: List<String>, //(пока что пустой массив отправлять)
                      val divisions: String, //(пока что пустой строкой отправлять)
                      val image_upload: ImageBitmap?

    ): GoodsAndServicesIntents()

    object Discharge: GoodsAndServicesIntents()

    object Characteristics: GoodsAndServicesIntents()

    data class ReadyDischarge( val isBu: Int, val manufacturer: String,

                               val numberManufacturer: String, val postavka: String

    ): GoodsAndServicesIntents()

    data class DeleteGoodOrService ( val coroutineScope: CoroutineScope

    ) : GoodsAndServicesIntents()

    data class InputTextSearchComponent( val text: String ): GoodsAndServicesIntents()

}