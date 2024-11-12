package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.ContragentResponseModel
import com.model.CurrencyResponseModel

class GetCurrencyUseCase (

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute ( ): List<CurrencyResponseModel> {

        return client.getCurrency ()

    }
}