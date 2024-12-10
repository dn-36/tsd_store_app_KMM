package com.arrival_and_consumption_goods.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import kotlinx.coroutines.CoroutineScope

sealed class ArrivalAndConsumptionIntents {

    data class ArrivalOrConsumption ( val coroutineScope: CoroutineScope,

                                      val isPush: Int ) : ArrivalAndConsumptionIntents()

    object BackFromDataEntry : ArrivalAndConsumptionIntents()

    object BackFromAddProducts : ArrivalAndConsumptionIntents()

    object NoDelete : ArrivalAndConsumptionIntents()

    data class OpenDeleteComponent ( val item: StoreResponseArrivalAndConsumption? )

        : ArrivalAndConsumptionIntents()

    data class Next(

        val description: String,

        val idLegalEntityParish: Int?,

        val idLegalEntityExpense: Int?,

        val idContragentExpense: Int?,

        val idContragentParish: Int?,

        val idWarehouse: Int?

    ) : ArrivalAndConsumptionIntents()

    object BackFromListProducts : ArrivalAndConsumptionIntents()

    data class SelectProducts(val selectedProducts: ProductArrivalAndConsumption) :

        ArrivalAndConsumptionIntents()

    data class Ready( val count: String ) : ArrivalAndConsumptionIntents()

    object  CanselScanner : ArrivalAndConsumptionIntents()

    data class AddProductScanner( val name: String ) : ArrivalAndConsumptionIntents()

    data class OpenUpdateDataEntry (

        val coroutineScope: CoroutineScope,

        val item: StoreResponseArrivalAndConsumption

    ) : ArrivalAndConsumptionIntents()

    data class Update( val coroutineScope: CoroutineScope ) : ArrivalAndConsumptionIntents()

    object SelectFromList : ArrivalAndConsumptionIntents()

    data class CreateArrivalOrConsumption( val coroutineScope: CoroutineScope ) :

        ArrivalAndConsumptionIntents()

    data class DeleteArrivalOrConsumption( val coroutineScope: CoroutineScope ) :

        ArrivalAndConsumptionIntents()

    data class GetArrivalAndConsumptionGoods( val coroutineScope: CoroutineScope ) :

        ArrivalAndConsumptionIntents()

    object ScannerCamera : ArrivalAndConsumptionIntents()

    object ScannerZebraUsb : ArrivalAndConsumptionIntents()

    data class CanselSelectedProduct ( val index: Int) : ArrivalAndConsumptionIntents()

    data class LongPressItem( val index: Int ): ArrivalAndConsumptionIntents()

    object OnePressItem: ArrivalAndConsumptionIntents()

    data class AddNewGoodOrService ( val sku: String ): ArrivalAndConsumptionIntents()


    data class InputTextSearchComponent( val text: String ): ArrivalAndConsumptionIntents()

    data class CreateGoodOrService ( val coroutineScope: CoroutineScope, val name: String,
        //val video_youtube: String,
        //val ediz_id: Int?,
                                    val category_id: Int?,
                                    val is_product: Int,
        //val is_sale: Int,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
        //val system_category_id: Int?,
        //val is_view_sale: Int,
        //val is_order: Int,
        //val is_store: Int,
        //val is_store_view: Int,
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
        //val tags: List<String>, //(пока что пустой массив отправлять)
        //val variantes: List<String>, //(пока что пустой массив отправлять)
        //val divisions: String, //(пока что пустой строкой отправлять)
                                    val image_upload: ImageBitmap?

    ): ArrivalAndConsumptionIntents()

    data class BackFromCreateGoodOrService(val coroutineScope: CoroutineScope

    ): ArrivalAndConsumptionIntents()

}