package com.arrival_and_consumption_goods

import androidx.compose.runtime.Composable
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption

expect class DetailScreen(){

    @Composable
    fun Content( deviceId: Int, onBackPressed:() -> Unit,

                listProducts: List<AllProductArrivalAndConsumption>,

                onClickAdd: (sku: String ) -> Unit,

                onClickNewProductAdd: (sku: String ) -> Unit )

}