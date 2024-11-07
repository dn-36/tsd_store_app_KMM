package com.project.network.crm_network.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement


@Serializable
data class ServiceItem(

    val type_id: Int, // ID поля из items

    val name: String, // Значение поля

)





