package org.example.project.presentation.feature.qr_code_main.screens.product_search.viewmodel

sealed class ProductSearchIntent {
    data class EnterBarcode(val barcode: String) : ProductSearchIntent()
    data class EnterSearchProduct(val text: String) : ProductSearchIntent()
    data class SelectPrinter(val item: String) : ProductSearchIntent()
    object Search : ProductSearchIntent()
}