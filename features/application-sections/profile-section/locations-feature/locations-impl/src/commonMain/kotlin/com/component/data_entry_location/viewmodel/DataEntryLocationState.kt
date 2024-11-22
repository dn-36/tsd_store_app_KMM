package com.component.data_entry_location.viewmodel

import com.model.ContragentsResponseModel
import com.model.EntityContragentsModel

data class DataEntryLocationState (

    val isSet: Boolean = true,


    val name: String = "",

    val phone: String = "",

    val email: String = "",

    val weChat: String = "",

    val whatsApp: String = "",

    val telegram: String = "",

    val address: String = "",

    val other: String = "",

    val contragent: String = "",

    val entity: String = "",


    val expendedContragents: Boolean = false,

    val expendedEntity: Boolean = false,


    val filteredListContragents: List<ContragentsResponseModel> = emptyList(),

    val filteredListEntity: List<EntityContragentsModel> = emptyList(),

    val selectedContragent: ContragentsResponseModel? = null,

    val selectedEntity: EntityContragentsModel? = null

)