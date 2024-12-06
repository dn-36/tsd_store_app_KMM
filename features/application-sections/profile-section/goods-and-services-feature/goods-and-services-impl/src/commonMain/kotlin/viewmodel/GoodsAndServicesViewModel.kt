package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ProductsMenuScreenApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
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

    val getUnitsMeasurementUseCase: GetUnitsGoodsAndServicesUseCase

) : NetworkViewModel() {

    var state by mutableStateOf(GoodsAndServicesState())

    fun processIntents( intent: GoodsAndServicesIntents ) {

        when ( intent ) {

            is GoodsAndServicesIntents.GetGoodsAndServices -> {

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    if ( state.isSet ) {

                        val listProducts = getGoodsAndServicesUseCase.execute()

                        state = state.copy(

                            listProducts = listProducts,

                            isSet = false

                        )
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