package com.project.network.crm_network.model

import kotlinx.serialization.Contextual
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
    val type_id: Int,  // Обязательный id поля из items
    val name: String?, // Значение в зависимости от типа
    //val file: Int? = null,      // Поле для файлов, отправляется как 0 для типов file, stl, obj, fbx
    //val filename: Int? = null,  // Имя файла, также 0 для типов file, stl, obj, fbx
    //val req: Int,      // 1 для обязательного, 0 для необязательного
    //val type: String?  // Тип данных, например, text, alltext, man, specification и т.д.
)


