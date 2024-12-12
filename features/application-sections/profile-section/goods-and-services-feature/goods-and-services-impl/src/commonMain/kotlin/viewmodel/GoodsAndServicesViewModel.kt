package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import com.ProductsMenuScreenApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.core_app.utils.imageBitmapToBase64
import com.project.network.Navigation
import domain.usecases.CreateGoodOrServiceUseCase
import domain.usecases.DeleteGoodOrServiceUseCase
import domain.usecases.GetCategoryUseCase
import domain.usecases.GetCharacteristicsUseCase
import domain.usecases.GetGoodsAndServicesUseCase
import domain.usecases.GetSystemCategoryUseCase
import domain.usecases.GetUnitsGoodsAndServicesUseCase
import domain.usecases.UpdateGoodOrServiceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import model.ProductGoodsServicesModel
import org.koin.mp.KoinPlatform

class GoodsAndServicesViewModel (

    val getGoodsAndServicesUseCase: GetGoodsAndServicesUseCase,

    val getSystemCategoryUseCase: GetSystemCategoryUseCase,

    val getCategoryUseCase: GetCategoryUseCase,

    val getUnitsMeasurementUseCase: GetUnitsGoodsAndServicesUseCase,

    val createGoodOrServiceUseCase: CreateGoodOrServiceUseCase,

    val deleteGoodOrServiceUseCase: DeleteGoodOrServiceUseCase,

    val updateGoodOrServiceUseCase: UpdateGoodOrServiceUseCase,

    val getCharacteristicsUseCase: GetCharacteristicsUseCase

    ) : NetworkViewModel() {

    var state by mutableStateOf(GoodsAndServicesState())

    fun processIntents( intent: GoodsAndServicesIntents ) {

        when ( intent ) {

            is GoodsAndServicesIntents.SetScreen -> {

                //println("Update: ${state.updateItem}")

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    if ( state.isSet ) {

                        if ( intent.sku.isBlank() ) {

                            val listProducts = getGoodsAndServicesUseCase.execute()

                            state = state.copy(

                                listProducts = listProducts,

                                listFilteredProducts = listProducts,

                                isSet = false

                            )
                        }

                        else {

                            state = state.copy(

                                listSystemCategory = getSystemCategoryUseCase.execute(),

                                listCategory = getCategoryUseCase.execute(),

                                listUnitsMeasurement = getUnitsMeasurementUseCase.execute(),

                                isVisibilityDataEntry = true,

                                isSet = false

                            )


                        }

                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.InputTextSearchComponent -> {

                inputTextSearchComponent(intent.text)

            }

            is GoodsAndServicesIntents.Back -> back()

            is GoodsAndServicesIntents.BackFromDataEntry -> backFromDataEntry()

            is GoodsAndServicesIntents.BackFromAdditionalInformation -> {

                backFromAdditionalInformation()

            }

            is GoodsAndServicesIntents.BackFromCharacteristics -> backFromCharacteristics()

            is GoodsAndServicesIntents.BackFromDischarge -> backFromDischarge()

            is GoodsAndServicesIntents.OpenCreateDataEntry -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    state = state.copy(

                        listSystemCategory = getSystemCategoryUseCase.execute(),

                        listCategory = getCategoryUseCase.execute(),

                        listUnitsMeasurement = getUnitsMeasurementUseCase.execute(),

                        listCharacteristics = getCharacteristicsUseCase.execute(),

                        isVisibilityDataEntry = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.OpenUpdateDataEntry -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    state = state.copy(

                        listSystemCategory = getSystemCategoryUseCase.execute(),

                        listCategory = getCategoryUseCase.execute(),

                        listUnitsMeasurement = getUnitsMeasurementUseCase.execute(),

                        listCharacteristics = getCharacteristicsUseCase.execute(),

                        updateItem = intent.item,

                        isVisibilityDataEntry = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.CreateGoodOrService -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    val imageBase64 = if ( state.image_upload != null ) imageBitmapToBase64(

                        state.image_upload!! ) else null

                    createGoodOrServiceUseCase.execute( name = state.name,

                        video_youtube = state.video_youtube, ediz_id = state.ediz_id,

                        category_id = state.category_id, is_product = state.is_product,

                        is_sale = state.is_sale, system_category_id = state.system_category_id,

                        is_view_sale = state.is_view_sale, is_order = state.is_order,

                        is_store = state.is_store, is_store_view = state.is_store_view,

                        is_test = state.is_test, is_arenda = state.is_arenda,

                        is_zakaz = state.is_zakaz, is_ves = state.is_ves,

                        is_serial_nomer = state.is_serial_nomer,

                        is_date_fabrica = state.is_date_fabrica,

                        is_markirovka = state.is_markirovka,

                        is_bu = state.isBu , sku = state.sku, text_image = state.text_image,

                        creater = state.manufacturer, nomer_creater = state.numberManufacturer,

                        postavka = state.postavka, price = state.price, tags = state.tags,

                        divisions = state.divisions, variantes = state.variantes,

                        image_upload = imageBase64 )

                    val listProducts = getGoodsAndServicesUseCase.execute()

                    state = state.copy (

                        isVisibilityAdditionalInformationComponent = false,

                        listProducts = listProducts,

                        listFilteredProducts = listProducts,

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.UpdateGoodOrService -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    val imageBase64 = if ( state.image_upload != null ) imageBitmapToBase64( state.image_upload!! ) else null

                    updateGoodOrServiceUseCase.execute( id = state.updateItem!!.id?:0,

                        name = state.name, video_youtube = state.video_youtube,

                        ediz_id = state.ediz_id,

                        category_id = state.category_id, is_product = state.is_product,

                        is_sale = state.is_sale, system_category_id = state.system_category_id,

                        is_view_sale = state.is_view_sale, is_order = state.is_order,

                        is_store = state.is_store, is_store_view = state.is_store_view,

                        is_test = state.is_test, is_arenda = state.is_arenda,

                        is_zakaz = state.is_zakaz, is_ves = state.is_ves,

                        is_serial_nomer = state.is_serial_nomer,

                        is_date_fabrica = state.is_date_fabrica, is_markirovka = state.is_markirovka,

                        is_bu = state.isBu , sku = state.sku, text_image = state.text_image,

                        creater = state.manufacturer, nomer_creater = state.numberManufacturer,

                        postavka = state.postavka, price = state.price, tags = state.tags,

                        divisions = state.divisions, variantes = state.variantes,

                        image_upload = imageBase64 )

                    val listProducts = getGoodsAndServicesUseCase.execute()

                    state = state.copy (

                        isVisibilityAdditionalInformationComponent = false,

                        listProducts = listProducts,

                        listFilteredProducts = listProducts,

                        listAlphaTools = emptyList(),

                        updateItem = null

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.LongPressItem -> longPressItem( intent.index )

            is GoodsAndServicesIntents.OnePressItem -> onePressItem()

            is GoodsAndServicesIntents.NoDelete -> noDelete()

            is GoodsAndServicesIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is GoodsAndServicesIntents.DeleteGoodOrService -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    deleteGoodOrServiceUseCase.execute(state.updateItem!!.id?:0)

                    val listProducts = getGoodsAndServicesUseCase.execute()

                    state = state.copy (

                        isVisibilityDeleteComponent = false,

                        updateItem = null,

                        listProducts = listProducts,

                        listFilteredProducts = listProducts,

                        listAlphaTools = emptyList()

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.Next -> next( intent.name, intent.video_youtube, intent.ediz_id,

                intent.category_id, intent.is_product, intent.is_sale, intent.system_category_id,

                intent.is_view_sale, intent.is_order, intent.is_store, intent.is_store_view,

                intent.is_test, intent.is_arenda, intent.is_zakaz, intent.is_ves, intent.is_serial_nomer,

                intent.is_date_fabrica, intent.is_markirovka, intent.sku, intent.text_image,

                intent.price, intent.tags, intent.variantes, intent.divisions, intent.image_upload )

            is GoodsAndServicesIntents.Discharge -> discharge()

            is GoodsAndServicesIntents.Characteristics -> characteristics()

            is GoodsAndServicesIntents.ReadyDischarge -> readyDischarge( intent.isBu,

                intent.manufacturer, intent.numberManufacturer, intent.postavka )

        }

    }

    fun back(){

        val productsMenuScreen: ProductsMenuScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( productsMenuScreen.productsMenuScreen())

    }

    fun backFromDataEntry() {

        state = state.copy(

            isVisibilityDataEntry = false,

            updateItem = null

        )

    }

    fun backFromAdditionalInformation() {

        state = state.copy(

            isVisibilityAdditionalInformationComponent = false,

            isVisibilityDataEntry = true

        )

    }

    fun backFromDischarge() {

        state = state.copy(

            isVisibilityDischargeComponent = false,

            isVisibilityAdditionalInformationComponent = true

        )

    }

    fun backFromCharacteristics() {

        state = state.copy(

            isVisibilityCharacteristicsComponent = false,

            isVisibilityAdditionalInformationComponent = true

        )

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listProducts.size){0F}

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listProducts.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun openDeleteComponent ( item: ProductGoodsServicesModel ) {

        state = state.copy(

            updateItem = item,

            isVisibilityDeleteComponent = true

        )

    }

    fun noDelete () {

        state = state.copy(

            isVisibilityDeleteComponent = false,

            updateItem = null

        )

    }

    fun next ( name: String, video_youtube: String, ediz_id: Int?,

               category_id: Int?, is_product: Int, is_sale: Int,

               system_category_id: Int?, is_view_sale: Int, is_order: Int,

               is_store: Int, is_store_view: Int, is_test: Int?, is_arenda: Int?, is_zakaz: Int?,

               is_ves: Int?, is_serial_nomer: Int?, is_date_fabrica: Int?,

               is_markirovka: Int?, sku: String, text_image: String, price: Float?,

               tags: List<String>, variantes: List<String>, divisions: String,

               image: ImageBitmap? ) {

        state = state.copy(

            isVisibilityDataEntry = false,

            isVisibilityAdditionalInformationComponent = true,

            name = name,

            video_youtube = video_youtube,

            ediz_id = ediz_id,

            category_id = category_id,

            is_product = is_product,

            is_sale = is_sale,

            system_category_id = system_category_id,

            is_view_sale = is_view_sale,

            is_order = is_order,

            is_store = is_store,

            is_store_view = is_store_view,

            is_test = is_test,

            is_arenda = is_arenda,

            is_ves = is_ves,

            is_zakaz = is_zakaz,

            is_markirovka = is_markirovka,

            is_serial_nomer = is_serial_nomer,

            is_date_fabrica = is_date_fabrica,

            sku = sku,

            text_image = text_image,

            price = price,

            tags = tags,

            variantes = variantes,

            divisions = divisions,

            image_upload = image

        )

    }

    fun discharge () {

        state = state.copy(

            isVisibilityAdditionalInformationComponent = false,

            isVisibilityDischargeComponent = true

        )

    }

    fun characteristics () {

        state = state.copy(

            isVisibilityAdditionalInformationComponent = false,

            isVisibilityCharacteristicsComponent = true

        )

    }

    fun readyDischarge ( isBu: Int, manufacturer: String, numberManufacturer: String,

                         postavka: String ) {

        state = state.copy (

            isBu = isBu,

            manufacturer = manufacturer,

            numberManufacturer = numberManufacturer,

            postavka = postavka,

            isVisibilityAdditionalInformationComponent = true,

            isVisibilityDischargeComponent = false,

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listProducts.filter {

            val name = it.name.orEmpty()

            name.contains(text, ignoreCase = true)

        }

        state = state.copy (

            listFilteredProducts = newList

        )
    }

}