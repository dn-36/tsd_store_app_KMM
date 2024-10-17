package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.project.network.contragent_network.model.ContragentResponse
import product_network.model.Product

class GetProductsUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute( onGet: (listAllProducts: List<Product>) -> Unit ) {

        client.getProducts  (
            onGet
        )
    }
}