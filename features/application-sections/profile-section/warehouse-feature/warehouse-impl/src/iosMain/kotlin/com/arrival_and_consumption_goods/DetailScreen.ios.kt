package com.arrival_and_consumption_goods

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

actual class DetailScreen actual constructor() {
    @Composable
    actual fun Content(deviceId: Int, onBackPressed:() -> Unit,

                       listProducts: List<AllProductArrivalAndConsumption>,

                       onClickAdd: (sku: String ) -> Unit,

                       onClickNewProductAdd: (sku: String ) -> Unit ) {


    }

}