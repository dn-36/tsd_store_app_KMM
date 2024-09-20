package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class QRCodeMenuState(
    val titleProduct:String = "выбирите продукт",
    val imgBitmap: Bitmap? = null,
    val heightQRcode:Int = 30,
    val fontSize:Int = 10
   /* var alphaListItem:MutableState<Boolean> = mutableStateOf(false),
    var alphaListPrinter:MutableState<Boolean> = mutableStateOf(false),
    var alphaSettings:MutableState<Boolean> = mutableStateOf(false),
    var fontSize:MutableState<Float> = mutableStateOf(16f),
    var imageSize:MutableState<Float> = mutableStateOf(20f),
    var textItem:MutableState<String> = mutableStateOf(""),
    var textPrinter:MutableState<String> = mutableStateOf(""),
    var listItem:MutableList<String> = mutableListOf("дима","ника","гризлик"),
    var listPrinter:MutableList<String> = mutableListOf("one","two","three"),*/
)
