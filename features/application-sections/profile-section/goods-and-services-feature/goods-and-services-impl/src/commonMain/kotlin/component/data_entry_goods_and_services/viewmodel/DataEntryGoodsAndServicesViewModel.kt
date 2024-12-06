package component.data_entry_goods_and_services.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.CategoryGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

class DataEntryGoodsAndServicesViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryGoodsAndServicesState())

    fun processIntents ( intent: DataEntryGoodsAndServicesIntents) {

        when ( intent ) {

            is DataEntryGoodsAndServicesIntents.SetScreen -> setScreen( intent.lisCategory,

                intent.listSystemCategory, intent.listUnitsMeasurement )

            is DataEntryGoodsAndServicesIntents.InputTextName -> inputTextName(intent.text)

            is DataEntryGoodsAndServicesIntents.InputTextProductLink -> {

                inputTextProductLink(intent.text)

            }

            is DataEntryGoodsAndServicesIntents.InputTextSku -> inputTextSku(intent.text)

            is DataEntryGoodsAndServicesIntents.InputTextVideoYouTube -> {

                inputTextVideoYouTube(intent.text)

            }

            is DataEntryGoodsAndServicesIntents.InputTextPrice -> inputTextPrice(intent.text)

            is DataEntryGoodsAndServicesIntents.InputTextCategory -> {

                inputTextCategory(intent.text, intent.list)

            }

            is DataEntryGoodsAndServicesIntents.InputTextSystemCategory -> {

                inputTextSystemCategory(intent.text, intent.list)

            }

            is DataEntryGoodsAndServicesIntents.InputTextUnit -> {

                inputTextUni(intent.text, intent.list)

            }



            is DataEntryGoodsAndServicesIntents.MenuCategory -> menuCategory()

            is DataEntryGoodsAndServicesIntents.MenuSystemCategory -> menuSystemCategory()

            is DataEntryGoodsAndServicesIntents.MenuUnits -> menuUnits()

            is DataEntryGoodsAndServicesIntents.MenuForSale -> menuForSale()

            is DataEntryGoodsAndServicesIntents.MenuDisplayOnSite -> menuDisplayOnSite()

            is DataEntryGoodsAndServicesIntents.MenuUnderOrder -> menuUnderOrder()

            is DataEntryGoodsAndServicesIntents.MenuIsStock -> menuIsStock()

            is DataEntryGoodsAndServicesIntents.MenuDisplayStock -> menuDisplayStock()



            is DataEntryGoodsAndServicesIntents.SelectCategory -> selectCategory(intent.item)

            is DataEntryGoodsAndServicesIntents.SelectForSale -> selectForSale(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectDisplayOnSite -> selectDisplayOnSite(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectUnderOrder -> selectUnderOrder(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsStock -> selectIsStock(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectDisplayStock -> selectDisplayStock(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectSystemCategory -> {

                selectSystemCategory(intent.item)

            }

            is DataEntryGoodsAndServicesIntents.SelectUnit -> {

                selectUnit(intent.item)

            }


            is DataEntryGoodsAndServicesIntents.DeleteSelectedCategory -> deleteSelectedCategory()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedSystemCategory -> {

                deleteSelectedSystemCategory()

            }

            is DataEntryGoodsAndServicesIntents.DeleteSelectedUnit -> deleteSelectedUnit()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedForSale -> deleteSelectedForSale()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedDisplayOnSite -> deleteSelectedDisplayOnSite()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedUnderOrder -> deleteSelectedUnderOrder()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedIsStock -> deleteSelectedIsStock()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedDisplayStock -> deleteSelectedDisplayStock()

        }

    }

    fun setScreen( listCategory: List<CategoryGoodsServicesModel>,

                   listSystemCategory: List<SystemCategoryGoodsServicesModel>,

                   listUnits: List<UnitGoodsAndServicesModel> ){

        if ( state.isSet ) {

            state = state.copy(

                listFilteredCategory = listCategory,

                listFilteredSystemCategory = listSystemCategory,

                listFilteredUnits = listUnits ,

                isSet = false

            )
        }

    }

    fun inputTextName( text: String ) {

        state = state.copy(

        name = text

        )

    }

    fun inputTextProductLink( text: String ) {

        state = state.copy(

            productLink = text

        )

    }

    fun inputTextSku( text: String ) {

        state = state.copy(

            sku = text

        )

    }

    fun inputTextVideoYouTube( text: String ) {

        state = state.copy(

            videoYouTube = text

        )

    }

    fun inputTextPrice( text: String ) {

        state = state.copy(

            price = text

        )

    }

    fun inputTextCategory( text: String, list: List<CategoryGoodsServicesModel> ) {

        state = state.copy(

            category = text,

            listFilteredCategory = list

        )

    }

    fun inputTextSystemCategory( text: String, list: List<SystemCategoryGoodsServicesModel> ) {

        state = state.copy(

            systemCategory = text,

            listFilteredSystemCategory = list

        )

    }

    fun inputTextUni( text: String, list: List<UnitGoodsAndServicesModel> ) {

        state = state.copy(

            uniMeasurement = text,

            listFilteredUnits = list

        )

    }



    fun menuCategory(){

        state = state.copy(

            expendedCategory = !state.expendedCategory,

            expendedSystemCategory = false,

            expendedUnits = false

        )

    }

    fun menuSystemCategory(){

        state = state.copy(

            expendedSystemCategory = !state.expendedSystemCategory,

            expendedCategory = false,

            expendedUnits = false

        )

    }

    fun menuUnits(){

        state = state.copy(

            expendedUnits = !state.expendedSystemCategory,

            expendedCategory = false,

            expendedSystemCategory = false,

        )

    }

    fun menuForSale(){

        state = state.copy(

            expendedForSale = !state.expendedForSale,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuDisplayOnSite(){

        state = state.copy(

            expendedDisplayOnSite = !state.expendedDisplayOnSite,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuUnderOrder(){

        state = state.copy(

            expendedUnderOrder = !state.expendedUnderOrder,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuIsStock(){

        state = state.copy(

            expendedIsStock = !state.expendedIsStock,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuDisplayStock(){

        state = state.copy(

            expendedDisplayStock = !state.expendedDisplayStock,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }



    fun selectCategory( item: CategoryGoodsServicesModel ){

        state = state.copy(

        selectedCategory = item,

            expendedCategory = false

        )

    }

    fun selectSystemCategory( item: SystemCategoryGoodsServicesModel ){

        state = state.copy(

            selectedSystemCategory = item,

            expendedSystemCategory = false

        )

    }

    fun selectUnit( item: UnitGoodsAndServicesModel){

        state = state.copy(

            selectedUnit = item,

            expendedUnits = false

        )

    }

    fun selectForSale( index: Int ){

        state = state.copy(

            selectedForSale = index,

            expendedForSale = false

        )

    }

    fun selectDisplayOnSite( index: Int ){

        state = state.copy(

            selectedDisplayOnSite = index,

            expendedDisplayOnSite = false

        )

    }

    fun selectIsStock( index: Int ){

        state = state.copy(

            selectedIsStock = index,

            expendedIsStock = false

        )

    }

    fun selectDisplayStock( index: Int ){

        state = state.copy(

            selectedDisplayStock = index,

            expendedDisplayStock = false

        )

    }

    fun selectUnderOrder( index: Int ){

        state = state.copy(

            selectedUnderOrder = index,

            expendedUnderOrder = false

        )

    }




    fun deleteSelectedCategory(){

        state = state.copy(

            selectedCategory = null

        )

    }

    fun deleteSelectedSystemCategory(){

        state = state.copy(

            selectedSystemCategory = null

        )

    }

    fun deleteSelectedUnit(){

        state = state.copy(

            selectedUnit = null

        )

    }

    fun deleteSelectedForSale(){

        state = state.copy(

            selectedForSale = 1

        )

    }

    fun deleteSelectedDisplayOnSite(){

        state = state.copy(

            selectedDisplayOnSite = 1

        )

    }

    fun deleteSelectedUnderOrder(){

        state = state.copy(

            selectedUnderOrder = 0

        )

    }

    fun deleteSelectedIsStock(){

        state = state.copy(

            selectedIsStock = 1

        )

    }

    fun deleteSelectedDisplayStock(){

        state = state.copy(

            selectedDisplayStock = 1

        )

    }

}