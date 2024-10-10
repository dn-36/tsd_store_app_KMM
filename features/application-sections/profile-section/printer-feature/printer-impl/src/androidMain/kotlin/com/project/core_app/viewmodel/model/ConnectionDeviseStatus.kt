package com.project.core_app.viewmodel.model

import androidx.compose.ui.graphics.Color

enum class ConnectionDeviseStatus(val text: String,val colorText: Color) {
    IsConnected("Подключен к принтеру",Color.Green),
    IsNotConnected("Не подключен к принтеру",Color.Red),
    NoShowStatus("",Color.White)

}