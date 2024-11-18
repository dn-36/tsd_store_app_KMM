package com.viewmodel

import com.model.LocationResponseModel
import kotlinx.coroutines.CoroutineScope

sealed class LocationsIntents {

    object Back:LocationsIntents()

    object BackFromDataEntry:LocationsIntents()

    data class SetScreen( val coroutineScope: CoroutineScope ): LocationsIntents()

    data class OpenDeleteComponent ( val item: LocationResponseModel ): LocationsIntents()

    data class OpenCreateDataEntryComponent (val coroutineScope: CoroutineScope ): LocationsIntents()

    data class OpenUpdateDataEntryComponent (val coroutineScope: CoroutineScope,

                                             val item: LocationResponseModel ): LocationsIntents()

    object NoDelete: LocationsIntents()

    data class DeleteLocation( val coroutineScope: CoroutineScope ): LocationsIntents()

    data class CreateLocation( val coroutineScope: CoroutineScope, val name: String?,

                               val email: String?, val phone: String?, val default: Int?,

                               val text: String?, val telegram: String?, val whatsapp: String?,

                               val wechat: String?, val point: List<Double>?, val adres: String,

                               val contragent_id: Int, val entity_id: Int, val workers: List<Int>,

                               val langs: List<Int> ): LocationsIntents()

    data class UpdateLocation( val coroutineScope: CoroutineScope, val name: String?,

                               val email: String?, val phone: String?, val default: Int?,

                               val text: String?, val telegram: String?, val whatsapp: String?,

                               val wechat: String?, val point: List<Double>?, val adres: String,

                               val contragent_id: Int, val entity_id: Int, val workers: List<Int>,

                               val langs: List<Int> ): LocationsIntents()

}