package org.example.project.presentation.feature.qr_code_menu.screens.product_search.datasource

import org.example.project.presentation.feature.qr_code_menu.screens.product_search.domain.RepositoryProductAPI
import product_network.ProductApiClient

class RepositoryProductImpl(private val apiClient: ProductApiClient): RepositoryProductAPI {
    override suspend fun getProduct(): List<String>  = apiClient.getProductNames().map { it.name?:"" }
}