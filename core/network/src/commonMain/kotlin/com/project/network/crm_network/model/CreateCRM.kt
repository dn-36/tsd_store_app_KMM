package com.project.network.crm_network.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CreateCRM(
    val service_id: Int?,
    val status_pay: Int? = 0,
    val verify_pay: Int? = 0,
    val task: String? = null,
    val to_local_id:Int?,
    val group_entity_id:Int?,
    val from_local_id:Int?,
    val status: String? = null,
    val price: String? = null,
    val arenda_id: Int? = null,
    val specification_id: Int? = null,
    val project_id: Int? = null,
    val entity_id: Int? = null,
    val our_entity_id: Int? = null,
    val text: String? = null,
    val statusid: Int? = 1,
    val items: List<ServiceItem>
)

@Serializable
data class ServiceItem(

    val type_id: Int, // ID поля из items

    val name: String, // Значение поля

)



