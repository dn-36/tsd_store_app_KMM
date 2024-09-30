package org.example.project.presentation.feature.qr_code.screens.product_search.domain

import org.example.project.presentation.core.models.ProductPresentationModel

interface RepositoryProductAPI {

    suspend fun getProduct():List<ProductPresentationModel>

}