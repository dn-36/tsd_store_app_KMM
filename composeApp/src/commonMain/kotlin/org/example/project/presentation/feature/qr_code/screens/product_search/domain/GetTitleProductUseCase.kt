package org.example.project.presentation.feature.qr_code.screens.product_search.domain

import org.example.project.presentation.core.models.ProductPresentationModel

class GetTitleProductUseCase(private val repository: RepositoryProductAPI) {

    suspend fun execute():List<ProductPresentationModel> = repository.getProduct()

}