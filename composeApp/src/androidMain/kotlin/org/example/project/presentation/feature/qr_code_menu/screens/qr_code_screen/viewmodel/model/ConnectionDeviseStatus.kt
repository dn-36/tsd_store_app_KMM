package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.model

import androidx.compose.ui.graphics.Color
import org.w3c.dom.Text

enum class ConnectionDeviseStatus(val text: String,val colorText: Color) {
    IsConnected("Подключен к принтеру",Color.Green),
    IsNotConnected("Не подключен к принтеру",Color.Red),
    NoShowStatus("",Color.White)

}