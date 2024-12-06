package component.data_entry_goods_and_services.viewmodel

import model.CategoryGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

sealed class DataEntryGoodsAndServicesIntents {

    data class SetScreen( val lisCategory: List<CategoryGoodsServicesModel>,

        val listSystemCategory: List<SystemCategoryGoodsServicesModel>,

        val listUnitsMeasurement: List<UnitGoodsAndServicesModel>

    ): DataEntryGoodsAndServicesIntents()


    data class InputTextName( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextProductLink( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextSku( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextVideoYouTube( val text: String ): DataEntryGoodsAndServicesIntents()

    data class InputTextPrice( val text: String ): DataEntryGoodsAndServicesIntents()

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



    object DeleteSelectedCategory: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedSystemCategory: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedUnit: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedForSale: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedDisplayOnSite: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedUnderOrder: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedIsStock: DataEntryGoodsAndServicesIntents()

    object DeleteSelectedDisplayStock: DataEntryGoodsAndServicesIntents()



    object MenuCategory: DataEntryGoodsAndServicesIntents()

    object MenuSystemCategory: DataEntryGoodsAndServicesIntents()

    object MenuUnits: DataEntryGoodsAndServicesIntents()

    object MenuForSale: DataEntryGoodsAndServicesIntents()

    object MenuDisplayOnSite: DataEntryGoodsAndServicesIntents()

    object MenuUnderOrder: DataEntryGoodsAndServicesIntents()

    object MenuIsStock: DataEntryGoodsAndServicesIntents()

    object MenuDisplayStock: DataEntryGoodsAndServicesIntents()

}