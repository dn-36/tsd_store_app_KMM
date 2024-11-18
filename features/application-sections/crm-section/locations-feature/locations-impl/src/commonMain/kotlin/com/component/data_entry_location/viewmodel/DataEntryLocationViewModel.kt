package com.component.data_entry_location.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.model.ContragentsResponseModel
import com.model.EntityContragentsModel
import com.model.LocationResponseModel

class DataEntryLocationViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryLocationState())

    fun processIntents ( intent: DataEntryLocationIntents) {

        when ( intent ) {

            is DataEntryLocationIntents.SetScreen -> setScreen(intent.item, intent.listContragents)

            is DataEntryLocationIntents.InputTextTitle -> inputTextTitle(intent.text)

            is DataEntryLocationIntents.InputTextPhone -> inputTextPhone(intent.text)

            is DataEntryLocationIntents.InputTextEmail -> inputTextEmail(intent.text)

            is DataEntryLocationIntents.InputTextTelegram -> inputTextTelegram(intent.text)

            is DataEntryLocationIntents.InputTextWeChat -> inputTextWeChat(intent.text)

            is DataEntryLocationIntents.InputTextWhatsApp -> inputTextWhatsApp(intent.text)

            is DataEntryLocationIntents.InputTextAddress -> inputTextAddress(intent.text)

            is DataEntryLocationIntents.InputTextOther -> inputTextOther(intent.text)

            is DataEntryLocationIntents.DeleteSelectedContragent -> deleteSelectedContragent()

            is DataEntryLocationIntents.MenuContragents -> menuContragents()

            is DataEntryLocationIntents.SelectContragent -> selectContragent(intent.item)

            is DataEntryLocationIntents.DeleteSelectedEntity -> deleteSelectedEntity()

            is DataEntryLocationIntents.MenuEntity -> menuEntity()

            is DataEntryLocationIntents.SelectEntity -> selectEntity(intent.item)

        }

    }

    fun setScreen(item: LocationResponseModel? ,listContragents: List<ContragentsResponseModel>) {

        if (state.isSet) {

            val newListEntity = mutableListOf<EntityContragentsModel>()

            listContragents.forEach { it ->

                it.entities.forEach { entity ->

                newListEntity.add(entity)

                }

            }

            state = state.copy(

                filteredListContragents = listContragents,

                filteredListEntity = newListEntity,

                name = item?.name?:"",

                phone = item?.phone?:"",

                other = item?.text?:"",

                email = item?.email?:"",

                address = item?.adres?:"",

                selectedContragent = listContragents.find { it.id == item?.contragent?.id },

                selectedEntity = newListEntity.find { it.id == item?.entity?.id },

                isSet = false

            )

        }

    }

    fun inputTextTitle(text: String) {

        state = state.copy(

            name = text

        )

    }

    fun inputTextPhone(text: String) {

        state = state.copy(

            phone = text

        )

    }

    fun inputTextEmail(text: String) {

        state = state.copy(

            email = text

        )

    }

    fun inputTextTelegram(text: String) {

        state = state.copy(

            telegram = text

        )

    }

    fun inputTextWeChat(text: String) {

        state = state.copy(

            weChat = text

        )

    }

    fun inputTextWhatsApp(text: String) {

        state = state.copy(

            whatsApp = text

        )

    }

    fun inputTextOther(text: String) {

        state = state.copy(

            other = text

        )

    }

    fun inputTextAddress(text: String) {

        state = state.copy(

            address = text

        )

    }

    fun menuContragents() {

        state = state.copy(

            expendedContragents = !state.expendedContragents,

            expendedEntity = false

        )

    }

    fun menuEntity() {

        state = state.copy(

            expendedEntity = !state.expendedEntity,

            expendedContragents = false

        )

    }

    fun selectContragent(item: ContragentsResponseModel) {

        state = state.copy(

            selectedContragent = item,

            expendedContragents = false

        )

    }

    fun deleteSelectedContragent() {

        state = state.copy(

            selectedContragent = null

        )

    }

    fun selectEntity(item: EntityContragentsModel) {

        state = state.copy(

            selectedEntity = item,

            expendedEntity = false

        )

    }

    fun deleteSelectedEntity() {

        state = state.copy(

            selectedEntity = null

        )

    }

}