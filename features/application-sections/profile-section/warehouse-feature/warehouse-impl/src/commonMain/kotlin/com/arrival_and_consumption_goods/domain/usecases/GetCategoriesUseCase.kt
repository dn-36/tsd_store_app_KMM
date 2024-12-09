package com.arrival_and_consumption_goods.domain.usecases

import com.arrival_and_consumption_goods.model.CategoryModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi

class GetCategoriesUseCase (

    private val client: ArrivalAndConsumptionClientApi,

    ) {

    suspend fun execute(): List<CategoryModel> {

        return client.getCategories ()
    }
}