package com.profile.profile.udpPlayer.screens.product_search.domain

import com.profile.profile.udpPlayer.core.ProductPresentationModel

interface RepositoryProductAPI {

    suspend fun getProduct():List<ProductPresentationModel>

}