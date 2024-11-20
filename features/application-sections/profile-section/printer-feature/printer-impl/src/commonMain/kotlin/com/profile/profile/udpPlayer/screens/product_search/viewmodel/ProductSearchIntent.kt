package com.profile.profile.udpPlayer.screens.product_search.viewmodel

import com.profile.profile.udpPlayer.core.ProductPresentationModel
import kotlinx.coroutines.CoroutineScope

sealed class ProductSearchIntent {
    data class SetScreen( val scope:CoroutineScope) : ProductSearchIntent()
    data class EnterSearchProduct(val text: String) : ProductSearchIntent()
    data class SelectProduct(val item: ProductPresentationModel) : ProductSearchIntent()
   // object Search : ProductSearchIntent()
}