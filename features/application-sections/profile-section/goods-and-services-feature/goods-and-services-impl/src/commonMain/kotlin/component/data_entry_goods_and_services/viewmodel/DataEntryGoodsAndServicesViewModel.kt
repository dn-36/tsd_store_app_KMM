package component.data_entry_goods_and_services.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.project.core_app.utils.urlImageToImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.CategoryGoodsServicesModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel

class DataEntryGoodsAndServicesViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryGoodsAndServicesState())

    fun processIntents ( intent: DataEntryGoodsAndServicesIntents) {

        when ( intent ) {

            is DataEntryGoodsAndServicesIntents.SetScreen -> setScreen( intent.coroutineScope,

                intent.lisCategory, intent.listSystemCategory, intent.listUnitsMeasurement,

                intent.sku, intent.updateItem )

            is DataEntryGoodsAndServicesIntents.InputTextName -> inputTextName(intent.text)

            is DataEntryGoodsAndServicesIntents.InputTextProductLink -> {

                inputTextProductLink(intent.text)

            }

            is DataEntryGoodsAndServicesIntents.InputTextSku -> inputTextSku(intent.text)

            is DataEntryGoodsAndServicesIntents.InputTextVideoYouTube -> {

                inputTextVideoYouTube(intent.text)

            }

            is DataEntryGoodsAndServicesIntents.InputTextDescriptionImage -> {

                inputTextDescriptionImage(intent.text)

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

            is DataEntryGoodsAndServicesIntents.MenuIsVes -> menuIsVes()

            is DataEntryGoodsAndServicesIntents.MenuIsZakaz -> menuIsZakaz()

            is DataEntryGoodsAndServicesIntents.MenuIsArenda -> menuIsArenda()

            is DataEntryGoodsAndServicesIntents.MenuMarkirovka -> menuMarkirovka()

            is DataEntryGoodsAndServicesIntents.MenuIsSerialNomer -> menuIsSerialNomer()

            is DataEntryGoodsAndServicesIntents.MenuDateFabrica -> menuDateFabrica()

            is DataEntryGoodsAndServicesIntents.MenuIsTest -> menuIsTest()

            is DataEntryGoodsAndServicesIntents.MenuGoodOrService -> menuGoodOrService()



            is DataEntryGoodsAndServicesIntents.SelectCategory -> selectCategory(intent.item)

            is DataEntryGoodsAndServicesIntents.SelectForSale -> selectForSale(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectDisplayOnSite -> selectDisplayOnSite(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectUnderOrder -> selectUnderOrder(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsStock -> selectIsStock(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectDisplayStock -> selectDisplayStock(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsVes -> selectIsVes(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsZakaz -> selectIsZakaz(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsArenda -> selectIsArenda(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsTest -> selectIsTest(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectMarkirovka -> selectMarkirovka(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectDateFabrica -> selectDateFabrica(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectIsSerialNomer -> selectIsSerialNomer(intent.index)

            is DataEntryGoodsAndServicesIntents.SelectSystemCategory -> {

                selectSystemCategory(intent.item)

            }

            is DataEntryGoodsAndServicesIntents.SelectUnit -> {

                selectUnit(intent.item)

            }

            is DataEntryGoodsAndServicesIntents.SelectGoodOrService -> {

                selectGoodOrService(intent.index)

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

            is DataEntryGoodsAndServicesIntents.DeleteSelectedGoodOrService -> deleteSelectedGoodOrService()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedIsTest -> deleteSelectedIsTest()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedIsZakaz -> deleteSelectedIsZakaz()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedIsArenda -> deleteSelectedIsArenda()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedIsVes -> deleteSelectedIsVes()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedMakirovka -> deleteSelectedMarkirovka()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedDateFabrica -> deleteSelectedDateFabrica()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedIsSerialNomer -> deleteSelectedIsSerialNomer()

            is DataEntryGoodsAndServicesIntents.DeleteSelectedPhoto -> deleteSelectedPhoto()


            is DataEntryGoodsAndServicesIntents.CheckTF -> checkTF()

        }

    }

    fun setScreen( coroutineScope: CoroutineScope,

                   listCategory: List<CategoryGoodsServicesModel>,

                   listSystemCategory: List<SystemCategoryGoodsServicesModel>,

                   listUnits: List<UnitGoodsAndServicesModel>,

                   sku: String, updateItem: ProductGoodsServicesModel? ) {

        if ( state.isSet ) {

            var newIsSale = Pair("Да",1)

            var newIsSaleView = Pair("Да",1)

            var newOrder = Pair("Нет",0)

            var newIsStore = Pair("Да",1)

            var newIsStoreView = Pair("Да",1)

            var newIsProduct = Pair("Товар",1)

            var newIsTest = Pair("Да",1)

            var newIsArenda= Pair("Да",1)

            var newIsZakaz = Pair("Нет",0)

            var newDateFabrica = Pair("Нет",0)

            var newIsSerialNomer = Pair("Нет",0)

            var newMarkirovka = Pair("Нет",0)

            var newIsVes = Pair("Да",1)

            if ( updateItem != null ) {

                if ( updateItem.is_sale != null ) {

                    newIsSale = if ( updateItem.is_sale == 0 ) Pair("Нет",0) else Pair("Да",1)

                }

                if ( updateItem.is_view_sale != null ) {

                    newIsSaleView = if ( updateItem.is_view_sale == 0 ) Pair("Нет",0) else Pair("Да",1)

                }

                if ( updateItem.is_zakaz != null ) {

                    newOrder = if ( updateItem.is_order == 0 ) Pair("Нет",0) else Pair("Да",1)

                }

                if ( updateItem.is_store != null ) {

                    newIsStore = if ( updateItem.is_store == 0 ) Pair("Нет",0) else Pair("Да",1)

                }

                if ( updateItem.is_store_view != null ) {

                    newIsStoreView = if ( updateItem.is_store_view == 0 ) Pair("Нет",0) else Pair("Да",1)

                }

                    if (updateItem.is_product != null) {

                        newIsProduct =
                            if (updateItem.is_product == 0) Pair("Услуга", 0) else Pair("Товар", 1)

                    }

                if (updateItem.is_test != null) {

                    newIsTest =
                        if (updateItem.is_test == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

                if (updateItem.is_ves != null) {

                    newIsVes =
                        if (updateItem.is_ves == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

                if (updateItem.is_zakaz != null) {

                    newIsZakaz =
                        if (updateItem.is_zakaz == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

                if (updateItem.is_arenda != null) {

                    newIsArenda =
                        if (updateItem.is_arenda == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

                if (updateItem.is_markirovka != null) {

                    newMarkirovka =
                        if (updateItem.is_markirovka == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

                if (updateItem.is_serial_nomer != null) {

                    newIsSerialNomer =
                        if (updateItem.is_serial_nomer == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

                if (updateItem.is_date_fabrica != null) {

                    newDateFabrica =
                        if (updateItem.is_date_fabrica == 0) Pair("Нет", 0) else Pair("Да", 1)

                }

            }

            coroutineScope.launch( Dispatchers.Main ) {

                val newImage = if (updateItem != null) urlImageToImageBitmap(
                    updateItem.image ?: ""
                ) else null

                state = state.copy(

                    listFilteredCategory = listCategory,

                    listFilteredSystemCategory = listSystemCategory,

                    listFilteredUnits = listUnits,

                    sku = if (updateItem == null) sku else updateItem.sku ?: "",

                    name = if (updateItem != null) updateItem.name ?: "" else "",

                    productLink = if (updateItem != null) updateItem.url ?: "" else "",

                    videoYouTube = if (updateItem != null) updateItem.video_youtube ?: "" else "",

                    price = if (updateItem != null) (updateItem.price ?: 0f).toString() else "",

                    selectedForSale = newIsSale,

                    selectedDisplayOnSite = newIsSaleView,

                    selectedUnderOrder = newOrder,

                    selectedIsStock = newIsStore,

                    selectedDisplayStock = newIsStoreView,

                    selectedIsVes = newIsVes,

                    selectedIsZakaz = newIsZakaz,

                    selectedIsArenda = newIsArenda,

                    selectedIsTest = newIsTest,

                    selectedDateFabrica = newDateFabrica,

                    selectedIsSerialNomer = newIsSerialNomer,

                    selectedMarkirovka = newMarkirovka,

                    selectedGoodOrService = newIsProduct,

                    selectedCategory = listCategory.find { it.id == updateItem?.category_id },

                    selectedSystemCategory = listSystemCategory.find { it.id == updateItem?.system_category_id?.toIntOrNull() },

                    selectedUnit = listUnits.find { it.id == updateItem?.ediz_id },

                    descriptionImage = if (updateItem != null) updateItem.text_image ?: "" else "",

                    image = if (updateItem != null) urlImageToImageBitmap(
                        updateItem.image ?: ""
                    ) else null,

                    isSet = false

                )
            }

           // println("NewImage ${state.image}")

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

    fun inputTextDescriptionImage( text: String ) {

        state = state.copy(

            descriptionImage = text

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

            unitMeasurement = text,

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

    fun menuIsSerialNomer(){

        state = state.copy(

            expendedIsSerialNomer = !state.expendedIsSerialNomer,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuIsArenda(){

        state = state.copy(

            expendedIsArenda = !state.expendedIsArenda,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }


    fun menuIsTest(){

        state = state.copy(

            expendedIsTest = !state.expendedIsTest,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuIsZakaz(){

        state = state.copy(

            expendedIsZakaz = !state.expendedIsZakaz,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuIsVes(){

        state = state.copy(

            expendedIsVes = !state.expendedIsVes,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuDateFabrica(){

        state = state.copy(

            expendedDateFabrica = !state.expendedDateFabrica,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuMarkirovka(){

        state = state.copy(

            expendedMarkirovka = !state.expendedMarkirovka,

            expendedCategory = false,

            expendedSystemCategory = false,

            )

    }

    fun menuGoodOrService(){

        state = state.copy(

            expendedGoodOrService = !state.expendedGoodOrService,

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

            selectedForSale = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedForSale = false

        )

    }

    fun selectDisplayOnSite( index: Int ){

        state = state.copy(

            selectedDisplayOnSite = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedDisplayOnSite = false

        )

    }

    fun selectIsStock( index: Int ){

        state = state.copy(

            selectedIsStock = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsStock = false

        )

    }

    fun selectDisplayStock( index: Int ){

        state = state.copy(

            selectedDisplayStock = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedDisplayStock = false

        )

    }

    fun selectUnderOrder( index: Int ){

        state = state.copy(

            selectedUnderOrder = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedUnderOrder = false

        )

    }

    fun selectIsVes( index: Int ){

        state = state.copy(

            selectedIsVes = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsVes = false

        )

    }

    fun selectIsTest( index: Int ){

        state = state.copy(

            selectedIsTest = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsTest = false

        )

    }

    fun selectIsArenda( index: Int ){

        state = state.copy(

            selectedIsArenda = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsArenda = false

        )

    }

    fun selectDateFabrica( index: Int ){

        state = state.copy(

            selectedDateFabrica = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedDateFabrica = false

        )

    }

    fun selectIsSerialNomer( index: Int ){

        state = state.copy(

            selectedIsSerialNomer = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsSerialNomer = false

        )

    }

    fun selectIsZakaz( index: Int ){

        state = state.copy(

            selectedIsZakaz = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedIsZakaz = false

        )

    }

    fun selectMarkirovka( index: Int ){

        state = state.copy(

            selectedMarkirovka = if ( index == 1 ) Pair("да",1) else Pair("нет",0),

            expendedMarkirovka = false

        )

    }

    fun selectGoodOrService( index: Int ) {

        state = state.copy(

            selectedGoodOrService =  if ( index == 1 ) Pair("Товар",1) else Pair("Услуга",0),

            expendedGoodOrService = false

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

            selectedForSale = Pair("да",1)

        )

    }

    fun deleteSelectedDisplayOnSite(){

        state = state.copy(

            selectedDisplayOnSite = Pair("да",1)

        )

    }

    fun deleteSelectedUnderOrder(){

        state = state.copy(

            selectedUnderOrder = Pair("нет",0)

        )

    }

    fun deleteSelectedIsStock(){

        state = state.copy(

            selectedIsStock = Pair("да",1)

        )

    }

    fun deleteSelectedDisplayStock(){

        state = state.copy(

            selectedDisplayStock = Pair("да",1)

        )

    }

    fun deleteSelectedIsTest(){

        state = state.copy(

            selectedIsTest = Pair("да",1)

        )

    }

    fun deleteSelectedIsArenda(){

        state = state.copy(

            selectedIsArenda = Pair("да",1)

        )

    }

    fun deleteSelectedIsZakaz(){

        state = state.copy(

            selectedIsZakaz = Pair("да",1)

        )

    }

    fun deleteSelectedDateFabrica(){

        state = state.copy(

            selectedDateFabrica = Pair("да",1)

        )

    }

    fun deleteSelectedMarkirovka(){

        state = state.copy(

            selectedMarkirovka = Pair("да",1)

        )

    }

    fun deleteSelectedIsSerialNomer(){

        state = state.copy(

            selectedIsSerialNomer = Pair("да",1)

        )

    }

    fun deleteSelectedIsVes(){

        state = state.copy(

            selectedIsVes = Pair("да",1)

        )

    }

    fun deleteSelectedGoodOrService(){

        state = state.copy(

            selectedGoodOrService = Pair("Товар",1)

        )

    }

    fun deleteSelectedPhoto(){

        state = state.copy(

            image = null

        )

    }

    fun checkTF () {

        val newList = state.listColorBorderTF.toMutableList()

        var hasNull = true

        if ( state.name.isBlank() && state.selectedCategory == null ) {

            newList[0] = Color.Red

            newList[1] = Color.Red

            hasNull = false

        }

        else if ( state.name.isBlank() ) {

            newList[0] = Color.Red

            hasNull = false

        }
        else if ( state.selectedCategory == null ) {

            newList[1] = Color.Red

            hasNull = false

        }



        // Обновляем состояние

        state = state.copy(
            listColorBorderTF = newList,
            onCheck = hasNull
        )

    }

}