package com.project.project_conterol.component.data_entry.viewmodel

import com.project.project_conterol.model.ProjectResponseModel

sealed class DataEntryIntents {

   object MenuProject: DataEntryIntents()

   object MenuTime: DataEntryIntents()

   object OpenCloseCalendar: DataEntryIntents()

   data class SelectProject ( val item: ProjectResponseModel ) : DataEntryIntents()

   data class SelectHour ( val hour: String ) : DataEntryIntents()

   data class SelectMinutes ( val minutes: String ) : DataEntryIntents()

   object DeleteSelectedProject: DataEntryIntents()

   data class InputTextProject ( val text:String, val list: List<ProjectResponseModel>

   ): DataEntryIntents()

   data class InputTextDescription ( val text:String ): DataEntryIntents()

   data class SetScreen ( val listProjects: List<ProjectResponseModel> ): DataEntryIntents()

}