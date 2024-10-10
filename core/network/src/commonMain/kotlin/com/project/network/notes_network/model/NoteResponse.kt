package com.project.network.notes_network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement


fun removeHtmlTags(input: String): String {
    // Регулярное выражение для поиска HTML-тегов
    return input.replace(Regex("<[^>]*>"), "").trim()
}

@Serializable
data class NoteResponse(
    val active: Int?,
    val view: Int?,
    val fon: String?,
    val status: Int?,
    val creater: Int?,
    val sort: Int?,
    val ui: String?,
    val users: List<User>,
    val project: JsonElement? = null,
    val text: String?,
    val id: Int?,
    val name: String?,
    val user: User?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("open_link") val openLink: Int?,
    val chat: String? = null,
    @SerialName("count_new_message") val countNewMessage: Int?
){
    fun getPlainText(): String? {
        return text?.let {
            removeHtmlTags(it) // Удаляем HTML-теги из текста
        }
    }
}
