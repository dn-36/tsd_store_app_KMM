package org.example.project.presentation.feature.qr_code_main.screens.product_list.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.example.project.presentation.core.Navigator
import org.example.project.presentation.feature.qr_code_main.screens.product_list.ui.SearchScreen

class ProductSearchViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProductSearchState())
    val state: StateFlow<ProductSearchState> = _state

    fun proccesIntent(intent: ProductSearchIntent) {
        when (intent) {
            is ProductSearchIntent.EnterBarcode -> {
                _state.update { it.copy(barcode = intent.barcode) }
            }
            is ProductSearchIntent.EnterSearchProduct -> {
                _state.update { it.copy(searchText = intent.text) }
            }
            is ProductSearchIntent.SelectPrinter -> {
                _state.update { it.copy(selectedItem = intent.item) }
            }
            is ProductSearchIntent.Search -> {
                Navigator.navigator.push(SearchScreen())
            }
        }
    }
}