package com.arrival_and_consumption_goods.viewmodel_bluetooth_or_adapter

import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

sealed class ScannerBluetoothOrAdapterIntents {

    data class UpdateScanData( val text: String ): ScannerBluetoothOrAdapterIntents()

    data class CheckSku( val sku: String, val listProducts: List<AllProductArrivalAndConsumption>

    ): ScannerBluetoothOrAdapterIntents()

}