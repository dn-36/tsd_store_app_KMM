package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable

@Serializable
data class ContragentInfo(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val own: Int?
)
