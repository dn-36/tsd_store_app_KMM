package com.domain.usecases

import com.domain.repository.LocationsClientApi

class CreateLocationUseCase (

    private val client: LocationsClientApi,

    ) {

    suspend fun execute (  name: String?, email: String?, phone: String?,

                           default: Int?, text: String?,

                           telegram: String?, whatsapp: String?, wechat: String?,

                           point: List<Double>?, adres: String, contragent_id: Int,

                           entity_id: Int, workers: List<Int>, langs: List<Int> ) {

        return client.createLocation( name, email, phone, default, text, telegram, whatsapp,

            wechat, point, adres, contragent_id, entity_id, workers, langs)
    }
}