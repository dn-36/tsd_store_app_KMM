package com.arrival_and_consumption_goods.viewmodel_bluetooth_or_adapter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

class ScannerBluetoothOrAdapterViewModel: ViewModel() {

    var state by mutableStateOf(ScannerBluetoothOrAdapterState())

    fun processIntents( intent: ScannerBluetoothOrAdapterIntents ) {

        when( intent ){

            is ScannerBluetoothOrAdapterIntents.CheckSku -> {

                checkSku( intent.sku, intent.listProducts)

            }

            is ScannerBluetoothOrAdapterIntents.UpdateScanData -> updateScanData( intent.text )

        }

    }

    fun checkSku( sku: String, listProducts: List<AllProductArrivalAndConsumption> ) {

        val selectedProduct = listProducts.find { it.sku == sku }

        if (selectedProduct != null) {

            state = state.copy(

                checkSku = true

            )

        } else {

            state = state.copy(

                checkSku = false,

                textNewProduct = "Продукт в списке не обнаржуен \nВы хотите его добавить?"

            )

        }

    }

    fun updateScanData( text: String ) {

        state = state.copy(

            scanData = text

        )

    }

}