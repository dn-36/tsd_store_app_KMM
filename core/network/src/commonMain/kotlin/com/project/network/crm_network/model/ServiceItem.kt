package com.project.network.crm_network.model

import kotlinx.serialization.Serializable


@Serializable
data class ServiceItem(

    val type_id: Int, // ID поля из items

    val name: String, // Значение поля

)






