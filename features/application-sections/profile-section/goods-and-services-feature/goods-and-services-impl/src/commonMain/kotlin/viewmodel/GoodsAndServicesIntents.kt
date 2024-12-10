package viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import model.ProductGoodsServicesModel

sealed class GoodsAndServicesIntents {

   object Back: GoodsAndServicesIntents()

   data class SetScreen(val coroutineScope: CoroutineScope, val sku: String ): GoodsAndServicesIntents()

   data class OpenCreateDataEntry ( val coroutineScope: CoroutineScope ): GoodsAndServicesIntents()
   data class CreateGoodOrService ( val coroutineScope: CoroutineScope

   ): GoodsAndServicesIntents()

   object BackFromDataEntry: GoodsAndServicesIntents()

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

    data class ReadyDischarge( val isBu: Int, val manufacturer: String,

                               val numberManufacturer: String, val postavka: String

    ): GoodsAndServicesIntents()

    data class DeleteGoodOrService ( val coroutineScope: CoroutineScope

    ) : GoodsAndServicesIntents()

}