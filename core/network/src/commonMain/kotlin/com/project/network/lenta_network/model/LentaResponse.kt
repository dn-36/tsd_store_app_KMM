package com.project.network.lenta_network.model

import kotlinx.serialization.Serializable


@Serializable
data class LentaResponse(
    val id: Int,
    val name: String?,
    val text: String?,
    val creater_id: Int,
    val company_id: Int,
    val created_at: String,
    val updated_at: String,
    val image: String?,
    val del: Int,
    val video: String?,
    val position_vertical: Int?,
    val contragent_id: Int?,
    val project_id: Int?,
    val contragent: Contragent?,
    val project: Project?,
    val blocks: List<Block>
)

@Serializable
data class Contragent(
    val id: Int?,
    val name: String?,
    val details: String? // Пример дополнительных полей
)

@Serializable
data class Project(
    val id: Int?,
    val name: String?,
    val description: String? // Пример дополнительных полей
)

@Serializable
data class Block(
    val id: Int?,
    val type: String?,
    val content: String?
)
