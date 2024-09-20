package org.example.project.presentation.feature.qr_code_menu.screens.product_search.viewmodel


data class ProductSearchState(
    val searchText: String = "",
    val selectedItem: String = "Выбранный элемент по умолчанию",
    val items: List<String> = emptyList()
)