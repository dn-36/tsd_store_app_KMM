package com.profile.profile.qr_code.screens.product_search.domain

import com.profile.profile.qr_code.core.ProductPresentationModel

interface RepositoryProductAPI {

    suspend fun getProduct():List<ProductPresentationModel>

}