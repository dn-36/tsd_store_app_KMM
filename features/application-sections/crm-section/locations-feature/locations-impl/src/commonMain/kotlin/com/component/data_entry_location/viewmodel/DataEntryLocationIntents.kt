package com.component.data_entry_location.viewmodel

import com.model.ContragentsResponseModel
import com.model.EntityContragentsModel
import com.model.LocationResponseModel

sealed class DataEntryLocationIntents {

    data class InputTextTitle(val text: String ): DataEntryLocationIntents()

    data class InputTextEmail( val text: String ): DataEntryLocationIntents()

    data class InputTextPhone( val text: String ): DataEntryLocationIntents()

    data class InputTextTelegram( val text: String ): DataEntryLocationIntents()

    data class InputTextWeChat( val text: String ): DataEntryLocationIntents()

    data class InputTextWhatsApp( val text: String ): DataEntryLocationIntents()

    data class InputTextAddress( val text: String ): DataEntryLocationIntents()

    data class InputTextOther( val text: String ): DataEntryLocationIntents()

    data class InputTextContragent( val text: String, val list:List<ContragentsResponseModel>

    ): DataEntryLocationIntents()

    data class InputTextEntity( val text: String, val list: List<EntityContragentsModel>

    ): DataEntryLocationIntents()

    data class SetScreen (

        val item: LocationResponseModel?,

        val listContragents: List<ContragentsResponseModel> ): DataEntryLocationIntents()

    object MenuContragents: DataEntryLocationIntents()

    data class SelectContragent ( val item: ContragentsResponseModel ): DataEntryLocationIntents()

    object DeleteSelectedContragent: DataEntryLocationIntents()

    object MenuEntity: DataEntryLocationIntents()

    data class SelectEntity ( val item: EntityContragentsModel ): DataEntryLocationIntents()

    object DeleteSelectedEntity: DataEntryLocationIntents()

}