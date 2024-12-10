package com.arrival_and_consumption_goods

import androidx.compose.runtime.Composable
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption


expect class ScannerZebraUsbScreen(){

    @Composable
     fun Content( listProducts: List<AllProductArrivalAndConsumption>,

                 onClickAdd: (sku: String ) -> Unit, onClickNewProductAdd: (sku: String ) -> Unit
    )

}