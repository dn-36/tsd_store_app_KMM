package com.arrival_and_consumption_goods

import androidx.compose.runtime.Composable

expect class DetailScreen(){

    @Composable
    fun Content( deviceId: Int, onBackPressed:() -> Unit)

}