package com.domain.usecases

import com.domain.repository.LocationsClientApi

class UpdateLocationUseCase (

    private val client: LocationsClientApi,

    ) {

    suspend fun execute (  id: Int, name: String?, email: String?, phone: String?,

                           default: Int?, text: String?,

                           telegram: String?, whatsapp: String?, wechat: String?,

                           point: List<Double>?, adres: String, contragent_id: Int,

                           entity_id: Int, workers: List<Int>, langs: List<Int> ) {

        return client.updateLocation( id, name, email, phone, default, text, telegram, whatsapp,

            wechat, point, adres, contragent_id, entity_id, workers, langs)
    }
}