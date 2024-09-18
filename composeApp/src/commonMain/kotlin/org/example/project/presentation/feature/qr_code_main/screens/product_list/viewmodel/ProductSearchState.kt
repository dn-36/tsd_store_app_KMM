package org.example.project.presentation.feature.qr_code_main.screens.product_list.viewmodel

data class ProductSearchState(
    val barcode: String = "",
    val searchText: String = "",
    val selectedItem: String = "Выбранный элемент по умолчанию",
    val items: List<String> = emptyList()
)