package com.project.network.locations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Worker(
    val id: Int?,
    val name: String?,
    val telegram: String?,
    val wechat: String?,
    val ui: String?,
    val email: String?,
    val phone: String?,
    val whatsapp: String?,
    val text: String?
)
