package com.arrival_and_consumption_goods.viewmodel

import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

sealed class ScannerZebraUsbIntents {

    data class UpdateScanData( val text: String ): ScannerZebraUsbIntents()

    data class CheckSku( val sku: String, val listProducts: List<AllProductArrivalAndConsumption>

    ): ScannerZebraUsbIntents()

}