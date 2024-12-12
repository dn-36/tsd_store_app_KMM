package com.arrival_and_consumption_goods

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption


actual class ScannerZebraUsbScreen actual constructor(){

    @Composable

    actual fun Content( listProducts: List<AllProductArrivalAndConsumption>,

                       onClickAdd: (sku: String ) -> Unit,

                       onClickNewProductAdd: (sku: String ) -> Unit,

                       onClickBack:() -> Unit){

        Box ( modifier = Modifier.fillMaxSize().background(Color.White),

             contentAlignment = Alignment.Center) {

            Text("Функция доступна только на android")

        }

    }

}