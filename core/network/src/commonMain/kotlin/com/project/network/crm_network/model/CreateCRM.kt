package com.project.network.crm_network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CreateCRM(
    val service_id: Int?,
    val status_pay: Int? = 0,
    val verify_pay: Int? = 0,
    val task: String? = null,
    val price: String? = null,
    val arenda_id: Int? = null,
    val specification_id: Int? = null,
    val project_id: Int? = null,
    val entity_id: Int? = null,
    val our_entity_id: Int? = null,
    val text: String? = null,
    val statusid: Int? = 1,
    val items: List<ServiceItem>? = listOf()
)

@Serializable
data class ServiceItem(
    val type_id: Int?,
    val name: String?,
    val value: JsonElement?
)
