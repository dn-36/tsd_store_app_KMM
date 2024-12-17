package com.arrival_and_consumption_goods

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

actual class ListDevicesScreen actual constructor() {
    @Composable
    actual fun Content( onClickItem:( id: Int ) -> Unit, onClickBack:() -> Unit ) {

        Box ( modifier = Modifier.fillMaxSize().background(Color.White),

            contentAlignment = Alignment.Center) {

            Text("Функция доступна только на android")

        }

    }

}