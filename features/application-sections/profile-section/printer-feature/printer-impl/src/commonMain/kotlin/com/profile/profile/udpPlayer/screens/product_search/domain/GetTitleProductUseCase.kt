package com.profile.profile.udpPlayer.screens.product_search.domain

import com.profile.profile.udpPlayer.core.ProductPresentationModel

class GetTitleProductUseCase(private val repository: RepositoryProductAPI) {

    suspend fun execute():List<ProductPresentationModel> = repository.getProduct()

}