package component.data_entry_goods_and_services.viewmodel

import kotlinx.coroutines.CoroutineScope
import model.CategoryGoodsServicesModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

sealed class DataEntryGoodsAndServicesIntents {

    data class SetScreen( val coroutineScope: CoroutineScope,

        val lisCategory: List<CategoryGoodsServicesModel>,

        val listSystemCategory: List<SystemCategoryGoodsServicesModel>,

        val listUnitsMeasurement: List<UnitGoodsAndServicesModel>,

        val sku: String, val updateItem: ProductGoodsServicesModel?

    ): DataEntryGoodsAndServicesIntents()


    data class InputTextName( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextProductLink( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextSku( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextVideoYouTube( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextPrice( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextDescriptionImage( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextCategory( val text: String,

                                  val list: List<CategoryGoodsServicesModel>

    ): DataEntryGoodsAndServicesIntents()

    data class InputTextSystemCategory( val text: String,

                                        val list: List<SystemCategoryGoodsServicesModel>

    ): DataEntryGoodsAndServicesIntents()

    data class InputTextUnit( val text: String, val list: List<UnitGoodsAndServicesModel>

    ): DataEntryGoodsAndServicesIntents()



    data class SelectCategory( val item: CategoryGoodsServicesModel

    ): DataEntryGoodsAndServicesIntents()

    data class SelectSystemCategory( val item: SystemCategoryGoodsServicesModel

    ): DataEntryGoodsAndServicesIntents()

    data class SelectUnit( val item: UnitGoodsAndServicesModel): DataEntryGoodsAndServicesIntents()

    data class SelectForSale( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectDisplayOnSite( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectUnderOrder( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectIsStock( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectDisplayStock( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectGoodOrService( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectIsTest( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectIsZakaz( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectIsArenda( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectIsVes( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectIsSerialNomer( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectDateFabrica( val index: Int ): DataEntryGoodsAndServicesIntents()

    data class SelectMarkirovka( val index: Int ): DataEntryGoodsAndServicesIntents()



    object DeleteSelectedCategory: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedSystemCategory: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedUnit: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedForSale: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedDisplayOnSite: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedUnderOrder: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsStock: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedDisplayStock: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedGoodOrService: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedPhoto: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsVes: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsZakaz: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsArenda: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsSerialNomer: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedMakirovka: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedDateFabrica: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsTest: DataEntryGoodsAndServicesIntents()



    object MenuCategory: DataEntryGoodsAndServicesIntents()

    object MenuSystemCategory: DataEntryGoodsAndServicesIntents()

    object MenuUnits: DataEntryGoodsAndServicesIntents()

    object MenuForSale: DataEntryGoodsAndServicesIntents()

    object MenuDisplayOnSite: DataEntryGoodsAndServicesIntents()

    object MenuUnderOrder: DataEntryGoodsAndServicesIntents()

    object MenuIsStock: DataEntryGoodsAndServicesIntents()

    object MenuDisplayStock: DataEntryGoodsAndServicesIntents()

    object MenuIsTest: DataEntryGoodsAndServicesIntents()

    object MenuIsArenda: DataEntryGoodsAndServicesIntents()

    object MenuIsZakaz: DataEntryGoodsAndServicesIntents()

    object MenuIsVes: DataEntryGoodsAndServicesIntents()

    object MenuIsSerialNomer: DataEntryGoodsAndServicesIntents()

    object MenuDateFabrica: DataEntryGoodsAndServicesIntents()

    object MenuMarkirovka: DataEntryGoodsAndServicesIntents()

    object MenuGoodOrService: DataEntryGoodsAndServicesIntents()


    object CheckTF : DataEntryGoodsAndServicesIntents()


    object ViewingPhoto : DataEntryGoodsAndServicesIntents()

    object CanselViewingPhoto : DataEntryGoodsAndServicesIntents()



    object DeleteVideoMobile : DataEntryGoodsAndServicesIntents()

}