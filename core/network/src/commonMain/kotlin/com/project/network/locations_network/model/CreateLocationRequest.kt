package com.project.network.locations_network.model

import com.project.network.locations_network.LocationsClient
import kotlinx.serialization.Serializable

@Serializable
data class CreateLocationRequest(
    val name: String?,
    val point: String?,
    val adres: String?,
    val contragent_id: String?,
    val email: String?,
    val phone: String?,
    val default: Int?,
    val text: String?,
    val telegram: String?,
    val whatsapp: String?,
    val wechat: String?,
    val entity_id: Int?,
    val workers: String?,
    val langs: String?
)
