package com.arrival_and_consumption_goods

import androidx.compose.runtime.Composable

expect class ListDevicesScreen(){

    @Composable
    fun Content( onClickItem:( id: Int ) -> Unit, onClickBack:() -> Unit )

}