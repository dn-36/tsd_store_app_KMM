package org.example.project.presentation.feature.qr_code.screens.product_search.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.feature.qr_code.screens.product_search.domain.RepositoryProductAPI
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.QRCodeMenuScreen

class ProductSearchViewModel(
    private val repository: RepositoryProductAPI
) : ViewModel() {
    private val _state = MutableStateFlow(ProductSearchState())
    val state: StateFlow<ProductSearchState> = _state
    private var isSetedScreen:Boolean = false

    fun proccesIntent(intent: ProductSearchIntent) {
        when (intent) {
            is ProductSearchIntent.SetScreen -> {
                if(isSetedScreen) return
                    isSetedScreen = true
                    intent.scope.launch {
                        _state.value =
                            _state.value.copy(
                                items = repository.getProduct()//.map { it.title?:"" }

                            )
                    }

            }
            is ProductSearchIntent.EnterSearchProduct -> {
                _state.update { it.copy(searchText = intent.text) }
            }
            is ProductSearchIntent.SelectProduct -> {
                _state.update { it.copy(selectedItem = intent.item.title?:"") }
                val screen = QRCodeMenuScreen()

                screen.product = intent.item
                println(screen.product)

                NavigatorComponent.navigator!!.push(screen)
            }
        }
    }
}