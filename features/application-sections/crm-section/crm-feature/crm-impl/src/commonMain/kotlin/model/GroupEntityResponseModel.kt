package model

import com.project.network.group_entity_network.model.Entity
import kotlinx.serialization.Serializable

data class GroupEntityResponseModel(

    val id: Int,
    val name: String,
    val company_id: Int,
    val ui: String,
    val created_at: String,
    val updated_at: String,

)
