package com.profile.profile.qr_code.screens.product_search.datasource

import com.profile.profile.qr_code.core.ProductPresentationModel
import com.profile.profile.qr_code.screens.product_search.domain.RepositoryProductAPI
import product_network.ProductApiClient

class RepositoryProductImpl(private val apiClient: ProductApiClient): RepositoryProductAPI {
    override suspend fun getProduct(): List<ProductPresentationModel>  =
        apiClient.getProductNames().map {
            ProductPresentationModel(
                it.name?:"",
                it.sku?:""
            )  }
}