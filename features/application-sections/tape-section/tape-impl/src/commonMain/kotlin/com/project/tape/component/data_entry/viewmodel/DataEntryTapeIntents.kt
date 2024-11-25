package com.project.tape.component.data_entry.viewmodel

import com.project.tape.model.ProjectResponseModel

sealed class DataEntryTapeIntents {

    data class SetScreen( val listProjects: List<ProjectResponseModel> ): DataEntryTapeIntents()

    data class InputTextName( val text: String ): DataEntryTapeIntents()

    data class InputTextDescription( val text: String ): DataEntryTapeIntents()


}