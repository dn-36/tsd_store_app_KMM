package com.project.project_conterol.component.data_entry_project_control.viewmodel

import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ServiceModel

sealed class DataEntryProjectControlIntents {

   object MenuProject: DataEntryProjectControlIntents()

   object MenuTime: DataEntryProjectControlIntents()

   object OpenCloseCalendar: DataEntryProjectControlIntents()

   data class SelectProject ( val item: ProjectResponseModel ) : DataEntryProjectControlIntents()

   data class SelectHour ( val hour: String ) : DataEntryProjectControlIntents()

   data class SelectMinutes ( val minutes: String ) : DataEntryProjectControlIntents()

   object DeleteSelectedProject: DataEntryProjectControlIntents()

   data class InputTextProject ( val text:String, val list: List<ProjectResponseModel>

   ): DataEntryProjectControlIntents()

   data class InputTextDescription ( val text:String ): DataEntryProjectControlIntents()

   data class SetScreen ( val listProjects: List<ProjectResponseModel>,

      val item: ServiceModel? ): DataEntryProjectControlIntents()

}