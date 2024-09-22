package org.example.project.presentation.feature.qr_code.screens.product_search.viewmodel

import kotlinx.coroutines.CoroutineScope
import org.example.project.presentation.core.models.ProductPresentationModel

sealed class ProductSearchIntent {
    data class SetScreen( val scope:CoroutineScope) : ProductSearchIntent()
    data class EnterSearchProduct(val text: String) : ProductSearchIntent()
    data class SelectProduct(val item: ProductPresentationModel) : ProductSearchIntent()
   // object Search : ProductSearchIntent()
}