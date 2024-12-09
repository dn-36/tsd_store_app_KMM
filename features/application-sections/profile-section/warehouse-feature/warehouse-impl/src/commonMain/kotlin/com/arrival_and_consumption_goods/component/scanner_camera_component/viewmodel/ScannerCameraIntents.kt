package com.arrival_and_consumption_goods.component.scanner_camera_component.viewmodel

import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

sealed class ScannerCameraIntents {

    data class CheckSku( val sku: String, val listProducts: List<AllProductArrivalAndConsumption>

    ): ScannerCameraIntents()

    data class NavigateToAddProduct( val sku: String ): ScannerCameraIntents()

}