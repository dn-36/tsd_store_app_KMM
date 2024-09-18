package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class DimaState(
    var alphaListItem:MutableState<Boolean> = mutableStateOf(false),
    var alphaListPrinter:MutableState<Boolean> = mutableStateOf(false),
    var alphaSettings:MutableState<Boolean> = mutableStateOf(false),
    var fontSize:MutableState<Float> = mutableStateOf(16f),
    var imageSize:MutableState<Float> = mutableStateOf(20f),
    var textItem:MutableState<String> = mutableStateOf(""),
    var textPrinter:MutableState<String> = mutableStateOf(""),
    var listItem:MutableList<String> = mutableListOf("дима","ника","гризлик"),
    var listPrinter:MutableList<String> = mutableListOf("one","two","three"),
)
