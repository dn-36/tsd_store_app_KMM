package com.arrival_and_consumption_goods.component.scanner_camera_component.viewmodel

import GoodsAndServicesScreenApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.project.network.Navigation
import org.koin.mp.KoinPlatform

class ScannerCameraViewModel: ViewModel() {

    var state by mutableStateOf(ScannerCameraState())

    fun processIntents ( intent: ScannerCameraIntents) {

        when ( intent ) {

            is ScannerCameraIntents.CheckSku -> checkSku( intent.sku, intent.listProducts )

        }

    }

    fun checkSku(sku: String, listProducts: List<AllProductArrivalAndConsumption>) {

        val selectedProduct = listProducts.find { it.sku == sku }

        if ( selectedProduct != null ) {

            state = state.copy(

                checkSku = true

            )

        }

        else {

        state = state.copy (

            checkSku = false,

            textNewProduct = "Продукт в списке не обнаржуен \nВы хотите его добавить?"

        )

        }

    }

}