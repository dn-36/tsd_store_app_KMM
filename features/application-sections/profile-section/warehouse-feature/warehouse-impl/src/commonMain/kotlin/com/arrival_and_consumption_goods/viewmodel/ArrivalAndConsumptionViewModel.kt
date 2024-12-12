package com.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.arrival_and_consumption_goods.domain.usecases.CreateArrivalOrConsumptionUseCase
import com.arrival_and_consumption_goods.domain.usecases.CreateGoodOrServiceUseCase
import com.arrival_and_consumption_goods.domain.usecases.DeleteArrivalOrConsumptionUseCase
import com.arrival_and_consumption_goods.domain.usecases.GetArrivalAndConsumptionUseCase
import com.arrival_and_consumption_goods.domain.usecases.GetCategoriesUseCase
import com.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.arrival_and_consumption_goods.domain.usecases.UpdateArrivalOrConsumptionUseCase
import com.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.core_app.utils.imageBitmapToBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel (

    val getContagentsUseCase: GetContagentsUseCase,

    val getProductsUseCase: GetProductsUseCase,

    val getWarehouseArrivalAndConsumptionUseCase: GetWarehouseArrivalAndConsumptionUseCase,

    val getArrivalAndConsumptionUseCase: GetArrivalAndConsumptionUseCase,

    val deleteArrivalOrConsumptionUseCase: DeleteArrivalOrConsumptionUseCase,

    val createArrivalOrConsumptionUseCase: CreateArrivalOrConsumptionUseCase,

    val updateArrivalOrConsumptionUseCase: UpdateArrivalOrConsumptionUseCase,

    val getCategoriesUseCase: GetCategoriesUseCase,

    val createGoodOrServiceUseCase: CreateGoodOrServiceUseCase

    ) : NetworkViewModel() {

    var state by mutableStateOf(ArrivalAndConsumptionState())

    fun processIntent (intent: ArrivalAndConsumptionIntents) {

        when (intent) {

            is ArrivalAndConsumptionIntents.ArrivalOrConsumption -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch (Dispatchers.IO) {

                        state = state.copy(

                            listAllWarehouse = getWarehouseArrivalAndConsumptionUseCase.execute(),

                            listAllContragent = getContagentsUseCase.execute(),

                            isUpdate = false,

                            isVisibilityDataEntryComponent = 1f,

                            isVisibilityDeleteComponent = 0f,

                            isPush = intent.isPush,

                            listProducts = getProductsUseCase.execute(),

                            listCategories = getCategoriesUseCase.execute()

                            )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteArrivalOrConsumptionUseCase.execute( ui = state.updatedItem!!.ui!! )

                    state = state.copy(

                        isVisibilityDeleteComponent = 0f,

                        isSet = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ArrivalAndConsumptionIntents.Update -> {

                if ( state.listSelectedProducts.size != 0 ) {

                    setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        updateArrivalOrConsumptionUseCase.execute(

                            productUi = state.updatedItem!!.ui!!,

                            description = state.description,

                            idLegalEntityParish = state.idLegalEntityParish,

                            idLegalEntityExpense = state.idLegalEntityExpense,

                            idContragentExpense = state.idContragentExpense,

                            idContragentParish = state.idContragentParish,

                            idWarehouse = state.idWarehouse,

                            isPush = state.isPush,

                            listProducts = state.listSelectedProducts
                        )

                        state = state.copy(

                            isVisibilityAddProductsComponent = 0f,

                            updatedEntityExpense = null,

                            updatedEntityParish = null,

                            updatedWarehouse = null,

                            updatedContragentParish = null,

                            updatedContragentExpense = null,

                            description = "",

                            listSelectedProducts = emptyList(),

                            isSet = true

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }

                }

            }

            is ArrivalAndConsumptionIntents.OpenUpdateDataEntry -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch (Dispatchers.IO) {

                    val newListContragents = getContagentsUseCase.execute()

                        val allWarehouse = getWarehouseArrivalAndConsumptionUseCase.execute()

                        val newListWarehouse = mutableListOf<WarehouseArrivalAndConsumption>()

                        allWarehouse.forEach { item ->

                                if( item.stores.isNotEmpty()) {

                                    newListWarehouse.add(item)

                                }

                        }

                        val listProducts = getProductsUseCase.execute()

                        val newSelectedProduct = mutableListOf<ProductArrivalAndConsumption>()

                            listProducts.forEach { item ->

                            intent.item.products!!.forEach {

                                if ( it!!.product_id == item.id ) {

                                    newSelectedProduct.add(
                                        ProductArrivalAndConsumption(

                                        product = item, count = it.count!!)
                                    )

                                }

                            }
                        }

                        val newContragentExpense =  newListContragents.find {

                            it.id == intent.item.contragent_id }

                        val newContragentParish =  newListContragents.find {

                            it.id == intent.item.contragent_push_id }

                    val newEntityExpense = newListContragents.find { contragent ->

                        contragent.entits?.any {

                            entity -> entity.id == intent.item.entity_push_id } == true
                    }

                    val newEntityParish = newListContragents.find { contragent ->

                        contragent.entits?.any {

                            entity -> entity.id == intent.item.entity_id } == true
                    }

                    val newWarehouse = newListWarehouse.find { warehouse ->

                        warehouse.stores.any { store -> store?.id == intent.item.store_id }
                    }


                        state = state.copy(

                            updatedEntityExpense = newEntityExpense,

                            updatedWarehouse = newWarehouse,

                            updatedEntityParish = newEntityParish,

                            updatedContragentExpense = newContragentExpense,

                            updatedContragentParish = newContragentParish,

                            listSelectedProducts = newSelectedProduct,

                            listProducts = listProducts,

                            updatedItem = intent.item,

                            isUpdate = true,

                            isVisibilityDataEntryComponent = 1f,

                            listAllWarehouse = allWarehouse,

                            isPush = intent.item.is_push!!,

                            description = intent.item.text,

                            listAllContragent = newListContragents


                        )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

            }

            }

            is ArrivalAndConsumptionIntents.CreateArrivalOrConsumption ->  {

                if ( state.listSelectedProducts.size != 0) {

                    setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        createArrivalOrConsumptionUseCase.execute(

                            description = state.description,

                            idLegalEntityParish = state.idLegalEntityParish,

                            idLegalEntityExpense = state.idLegalEntityExpense,

                            idContragentExpense = state.idContragentExpense,

                            idContragentParish = state.idContragentParish,

                            idWarehouse = state.idWarehouse,

                            isPush = state.isPush,

                            listProducts = state.listSelectedProducts
                        )

                        state = state.copy(

                            isVisibilityAddProductsComponent = 0f,

                            listSelectedProducts = emptyList(),

                            description = "",

                            isSet = true,

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }

                }

            }

            is ArrivalAndConsumptionIntents.Ready ->  ready( intent.count )

            is ArrivalAndConsumptionIntents.SelectProducts ->  selectProduct(

                intent.selectedProducts )

            is ArrivalAndConsumptionIntents.ScannerCamera ->  scannerCamera()

            is ArrivalAndConsumptionIntents.ScannerZebraUsb ->  scannerZebraUsb()

            is ArrivalAndConsumptionIntents.CanselScannerCamera ->  canselScannerCamera()

            is ArrivalAndConsumptionIntents.CanselScannerZebraUsb ->  canselScannerZebraUsb()

            is ArrivalAndConsumptionIntents.SelectFromList -> selectFromList()

            is ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods -> {

            intent.coroutineScope.launch (Dispatchers.IO) {

                if ( state.isSet ) {

                    val newListArrivalAndConsumption = getArrivalAndConsumptionUseCase.execute()

                        state = state.copy(

                            listArrivalOrConsumption = newListArrivalAndConsumption,

                            listFilteredArrivalOrConsumption = newListArrivalAndConsumption,

                            isSet = false

                        )

                }

                setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

            }

            }


            is ArrivalAndConsumptionIntents.BackFromDataEntry -> backFromDataEntry()

            is ArrivalAndConsumptionIntents.BackFromListProducts ->  backFromListProducts()

            is ArrivalAndConsumptionIntents.BackFromAddProducts ->  backFromAddProducts()


            is ArrivalAndConsumptionIntents.Next -> {

                next( intent.description, intent.idLegalEntityParish, intent.idLegalEntityExpense,

                    intent.idContragentExpense, intent.idContragentParish, intent.idWarehouse )

            }

            is ArrivalAndConsumptionIntents.CanselSelectedProduct -> {

                canselSelectedProduct( intent.index ) }

            is ArrivalAndConsumptionIntents.AddProductScanner -> addProductScanner( intent.name )

            is ArrivalAndConsumptionIntents.NoDelete -> noDelete()

            is ArrivalAndConsumptionIntents.OpenDeleteComponent -> {

                openDeleteComponent( intent.item!! ) }

            is ArrivalAndConsumptionIntents.LongPressItem -> longPressItem(intent.index)

            is ArrivalAndConsumptionIntents.OnePressItem -> onePressItem()

            is ArrivalAndConsumptionIntents.InputTextSearchComponent -> {

                inputTextSearchComponent(intent.text)

            }

            is ArrivalAndConsumptionIntents.AddNewGoodOrService -> {

                addNewGoodOrService( intent.sku )

            }

            is ArrivalAndConsumptionIntents.BackFromCreateGoodOrService -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    if ( state.lastScanner == "zebra usb" ) {

                        state = state.copy(

                            isVisibilityCreateGoodOrService = false,

                            isVisibilityScannerZebraUsbComponent = true

                            )
                    }

                    else if ( state.lastScanner == "camera" ) {

                        state = state.copy(

                            isVisibilityCreateGoodOrService = false,

                            isVisibilityScannerCameraComponent = 1f

                        )
                    }
                }
            }

            is ArrivalAndConsumptionIntents.CreateGoodOrService -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    val imageBase64 = if ( intent.image_upload != null ) imageBitmapToBase64(intent.image_upload) else null

                    createGoodOrServiceUseCase.execute( name = intent.name,

                        //video_youtube = intent.video_youtube, ediz_id = intent.ediz_id,

                        category_id = intent.category_id, is_product = intent.is_product,

                        is_sale = intent.is_sale,// system_category_id = intent.system_category_id,

                        is_view_sale = intent.is_view_sale, is_order = intent.is_order,

                         is_store = intent.is_store, is_store_view = intent.is_store_view,

                        sku = intent.sku, text_image = intent.text_image,

                        price = intent.price, //tags = intent.tags,

                        //divisions = intent.divisions, variantes = intent.variantes,

                        image_upload = imageBase64 )

                    if ( state.lastScanner == "zebra usb" ) {

                        state = state.copy(

                            isVisibilityCreateGoodOrService = false,

                            isVisibilityScannerZebraUsbComponent = true,

                            listProducts = getProductsUseCase.execute()

                        )
                    }

                    else if ( state.lastScanner == "camera" ) {

                        state = state.copy (

                            isVisibilityCreateGoodOrService = false,

                            isVisibilityScannerCameraComponent = 1F,

                            listProducts = getProductsUseCase.execute()

                        )
                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }
            }
        }
    }

    fun backFromAddProducts(){

        state = state.copy(

            isVisibilityAddProductsComponent = 0f,

            isVisibilityDataEntryComponent = 1f

        )

    }

    fun backFromListProducts(){

        state = state.copy(

            isVisibilityListProducts = 0f,

            isVisibilityAddProductsComponent = 1f

        )

    }


    fun backFromDataEntry(){

        state = state.copy(

            isVisibilityDataEntryComponent = 0f,

            updatedEntityExpense = null,

            updatedEntityParish = null,

            updatedWarehouse = null,

            updatedContragentParish = null,

            updatedContragentExpense = null,

            description = "",

            listSelectedProducts = emptyList()

        )

    }

    fun selectProduct( selectedProduct: ProductArrivalAndConsumption ){

        state = state.copy(

            isVisibilityCountProducts = 1f,

            isVisibilityListProducts = 0f,

            selectedProduct = selectedProduct

        )

    }

    fun scannerCamera() {

        state = state.copy(

            isVisibilityScannerCameraComponent = 1f,

            isVisibilityAddProductsComponent = 0f

        )

    }

    fun scannerZebraUsb() {

        state = state.copy(

            isVisibilityScannerZebraUsbComponent = true,

            isVisibilityAddProductsComponent = 0f

        )

    }


    fun ready ( count: String ) {

        if ( count.isNotBlank() && count.toDoubleOrNull() != null && count.toDouble() > 0  ) {

            val newProduct = state.selectedProduct

            newProduct!!.count = count.toDouble()

            val newList = state.listSelectedProducts.toMutableList()

            newList.add(newProduct)

            state = state.copy(

                selectedProduct = newProduct,

                isVisibilityCountProducts = 0f,

                isVisibilityAddProductsComponent = 1f,

                listSelectedProducts = newList,

                colorBorderCountTF = Color.LightGray

            )

        }

        else {

            state = state.copy (

                colorBorderCountTF = Color.Red

            )

        }

    }

    fun next ( description: String, idLegalEntityParish: Int?, idLegalEntityExpense: Int?,

               idContragentExpense: Int?, idContragentParish: Int?, idWarehouse: Int? ) {

        if ( idLegalEntityParish != null && idLegalEntityExpense != null &&

            idContragentExpense != null && idContragentParish != null && idWarehouse != null ) {

            state = state.copy(

                isVisibilityAddProductsComponent = 1f,

                isVisibilityDataEntryComponent = 0f,

                idContragentExpense = idContragentExpense,

                idContragentParish = idContragentParish,

                idLegalEntityExpense = idLegalEntityExpense,

                idLegalEntityParish = idLegalEntityParish,

                description = description,

                idWarehouse = idWarehouse,

            )

        }

    }

    fun canselSelectedProduct ( index: Int ) {

        val newList = state.listSelectedProducts.toMutableList()

        newList.removeAt(index)

    state = state.copy(

        listSelectedProducts = newList

    )

    }

    fun addProductScanner( sku: String ) {

    val selectedProduct = state.listProducts.find { it.sku == sku }

        if ( selectedProduct != null ) {

            state = state.copy(

                isVisibilityCountProducts = 1f,

                isVisibilityScannerCameraComponent = 0f,

                isVisibilityScannerZebraUsbComponent = false,

                selectedProduct = ProductArrivalAndConsumption( product = selectedProduct, count = 0.0 )

            )

        }

        else {



        }

    }

    fun selectFromList () {

        state = state.copy(

            isVisibilityListProducts = 1f,

            isVisibilityAddProductsComponent = 0f

            )

    }

    fun canselScannerCamera () {

        state = state.copy(

            isVisibilityScannerCameraComponent = 0f,

            isVisibilityAddProductsComponent = 1f

        )

    }

    fun canselScannerZebraUsb () {

        state = state.copy(

            isVisibilityScannerZebraUsbComponent = false,

            isVisibilityAddProductsComponent = 1f

        )

    }

    fun noDelete () {

        state = state.copy(

            isVisibilityDeleteComponent = 0f,

            updatedItem = null

        )

    }

    fun openDeleteComponent ( item: StoreResponseArrivalAndConsumption) {

        state = state.copy(

            isVisibilityDeleteComponent = 1f,

            updatedItem = item

        )

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listArrivalOrConsumption.size){ 0F }

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listArrivalOrConsumption.size){ 0F }

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listArrivalOrConsumption.filter {

            it.text.contains(text, ignoreCase = true)

        }

        state = state.copy (

            listFilteredArrivalOrConsumption = newList

        )
    }

    fun addNewGoodOrService( sku: String ) {

        if ( state.isVisibilityScannerZebraUsbComponent ) {

            state = state.copy(

                isVisibilityCreateGoodOrService = true,

                isVisibilityScannerZebraUsbComponent = false,

                lastScanner = "zebra usb",

                sku = sku

            )
        }

        else if ( state.isVisibilityScannerCameraComponent == 1F ) {

            state = state.copy(

                isVisibilityCreateGoodOrService = true,

                isVisibilityScannerCameraComponent = 0F,

                lastScanner = "camera",

                sku = sku

            )

        }

    }

}