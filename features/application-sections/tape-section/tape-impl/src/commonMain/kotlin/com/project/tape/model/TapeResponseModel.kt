package com.project.tape.model

data class TapeResponseModel(
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
    val contragent: ContragentModel?,
    val project: ProjectModel?,
    val blocks: List<BlockModel>
)

data class ContragentModel(
    val id: Int?,
    val name: String?,
    val details: String? // Пример дополнительных полей
)

data class ProjectModel(
    val id: Int?,
    val name: String?,
    val description: String? // Пример дополнительных полей
)

data class BlockModel(
    val id: Int?,
    val type: String?,
    val content: String?
)