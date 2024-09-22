package org.example.project.presentation.feature.qr_code.screens.product_search.viewmodel

import org.example.project.presentation.core.models.ProductPresentationModel

//import org.example.project.presentation.core.models.ProductPresentationModel


data class ProductSearchState(
    val searchText: String = "",
    val selectedItem: String = "Выбранный элемент по умолчанию",
    val items: List<ProductPresentationModel> = emptyList()
)