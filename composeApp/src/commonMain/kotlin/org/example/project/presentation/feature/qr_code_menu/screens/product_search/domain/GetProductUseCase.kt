package org.example.project.presentation.feature.qr_code_menu.screens.product_search.domain

class GetProductUseCase(private val repository: RepositoryProductAPI) {

    suspend fun execute():List<String> = repository.getProduct()

}