package com.profile.profile.qr_code.screens.product_search.domain

import com.profile.profile.qr_code.core.ProductPresentationModel

class GetTitleProductUseCase(private val repository: RepositoryProductAPI) {

    suspend fun execute():List<ProductPresentationModel> = repository.getProduct()

}