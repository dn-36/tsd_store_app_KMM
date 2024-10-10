package com.profile.profile.qr_code.screens.product_search.viewmodel

import com.profile.profile.qr_code.core.ProductPresentationModel
import kotlinx.coroutines.CoroutineScope

sealed class ProductSearchIntent {
    data class SetScreen( val scope:CoroutineScope) : ProductSearchIntent()
    data class EnterSearchProduct(val text: String) : ProductSearchIntent()
    data class SelectProduct(val item: ProductPresentationModel) : ProductSearchIntent()
   // object Search : ProductSearchIntent()
}