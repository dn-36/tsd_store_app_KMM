package com.project.tape.component.data_entry.viewmodel

import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectResponseModel
import kotlinx.coroutines.CoroutineScope

sealed class DataEntryTapeIntents {

    data class SetScreen( val listProjects: List<ProjectResponseModel>,

                          val listContragents: List<ContragentsResponseModel>

    ): DataEntryTapeIntents()

    data class InputTextName( val text: String ): DataEntryTapeIntents()

    data class InputTextDescription( val text: String ): DataEntryTapeIntents()

    data class InputTextContragent( val text: String,

                                    val list: List<ContragentsResponseModel>

    ): DataEntryTapeIntents()

    data class InputTextProject( val text: String,

                                 val list: List<ProjectResponseModel>

    ): DataEntryTapeIntents()



    data class SelectContragent( val item: ContragentsResponseModel ): DataEntryTapeIntents()

    data class SelectProject( val item: ProjectResponseModel ): DataEntryTapeIntents()


    object DeleteSelectedContragent: DataEntryTapeIntents()

    object DeleteSelectedProject: DataEntryTapeIntents()



    object MenuContragents: DataEntryTapeIntents()

    object MenuProjects: DataEntryTapeIntents()

    data class OpenMenuFiles( val coroutineScope: CoroutineScope) : DataEntryTapeIntents()

    object DeleteSelectedPhoto: DataEntryTapeIntents()


}