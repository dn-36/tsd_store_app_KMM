package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ProductsMenuScreenApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.core_app.utils.imageBitmapToBase64
import com.project.network.Navigation
import domain.usecases.CreateGoodOrServiceUseCase
import domain.usecases.GetCategoryUseCase
import domain.usecases.GetGoodsAndServicesUseCase
import domain.usecases.GetSystemCategoryUseCase
import domain.usecases.GetUnitsGoodsAndServicesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class GoodsAndServicesViewModel (

    val getGoodsAndServicesUseCase: GetGoodsAndServicesUseCase,

    val getSystemCategoryUseCase: GetSystemCategoryUseCase,

    val getCategoryUseCase: GetCategoryUseCase,

    val getUnitsMeasurementUseCase: GetUnitsGoodsAndServicesUseCase,

    val createGoodOrServiceUseCase: CreateGoodOrServiceUseCase

) : NetworkViewModel() {

    var state by mutableStateOf(GoodsAndServicesState())

    fun processIntents( intent: GoodsAndServicesIntents ) {

        when ( intent ) {

            is GoodsAndServicesIntents.SetScreen -> {

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    if ( state.isSet ) {

                        if ( intent.sku.isBlank() ) {

                            val listProducts = getGoodsAndServicesUseCase.execute()

                            state = state.copy(

                                listProducts = listProducts,

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

            is GoodsAndServicesIntents.Back -> back()

            is GoodsAndServicesIntents.BackFromDataEntry -> backFromDataEntry()

            is GoodsAndServicesIntents.OpenCreateDataEntry -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    state = state.copy(

                        listSystemCategory = getSystemCategoryUseCase.execute(),

                        listCategory = getCategoryUseCase.execute(),

                        listUnitsMeasurement = getUnitsMeasurementUseCase.execute(),

                        isVisibilityDataEntry = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is GoodsAndServicesIntents.CreateGoodOrService -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    val imageBase64 = if ( intent.image_upload != null ) imageBitmapToBase64(intent.image_upload) else null

                    createGoodOrServiceUseCase.execute( name = intent.name,

                        video_youtube = intent.video_youtube, ediz_id = intent.ediz_id,

                        category_id = intent.category_id, is_product = intent.is_product,

                        is_sale = intent.is_sale, system_category_id = intent.system_category_id,

                        is_view_sale = intent.is_view_sale, is_order = intent.is_order,

                        is_store = intent.is_store, is_store_view = intent.is_store_view,

                        sku = intent.sku, text_image = intent.text_image,

                        price = intent.price, tags = intent.tags,

                        divisions = intent.divisions, variantes = intent.variantes,

                        image_upload = imageBase64 )

                    state = state.copy (

                        isVisibilityDataEntry = false,

                        listProducts = getGoodsAndServicesUseCase.execute()

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

        }

    }

    fun back(){

        val productsMenuScreen: ProductsMenuScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( productsMenuScreen.productsMenuScreen())

    }

    fun backFromDataEntry() {

        state = state.copy(

            isVisibilityDataEntry = false

        )

    }

}