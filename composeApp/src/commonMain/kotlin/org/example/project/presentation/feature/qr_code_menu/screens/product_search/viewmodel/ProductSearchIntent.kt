package org.example.project.presentation.feature.qr_code_menu.screens.product_search.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class ProductSearchIntent {
    data class SetScreen( val scope:CoroutineScope) : ProductSearchIntent()
    data class EnterSearchProduct(val text: String) : ProductSearchIntent()
    data class SelectProduct(val item: String) : ProductSearchIntent()
   // object Search : ProductSearchIntent()
}