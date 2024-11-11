package com.project.project_conterol.component.data_entry.viewmodel

import DatePickerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.project_conterol.model.ProjectResponseModel
import org.koin.mp.KoinPlatform

class DataEntryViewModel : ViewModel() {

   var state by mutableStateOf(DataEntryState())

   fun processIntents ( intent: DataEntryIntents ) {

      when ( intent ) {

         is DataEntryIntents.InputTextProject -> inputTextProject( intent.text, intent.list )

         is DataEntryIntents.MenuProject -> menuProjects()

         is DataEntryIntents.MenuTime -> menuTime()

         is DataEntryIntents.OpenCloseCalendar -> openCloseCalendar()

         is DataEntryIntents.DeleteSelectedProject -> deleteSelectedProject()

         is DataEntryIntents.SelectProject -> selectProject(intent.item)

         is DataEntryIntents.SetScreen -> setScreen(intent.listProjects)

         is DataEntryIntents.SelectHour -> selectHour(intent.hour)

         is DataEntryIntents.SelectMinutes -> selectMinutes(intent.minutes)

         is DataEntryIntents.InputTextDescription -> inputTextDescription(intent.text)

      }

   }

   fun menuProjects () {

      state = state.copy(

         expendedProject = !state.expendedProject,

         isVisibilityCalendar = false,

         expendedTime = false

      )

   }

   fun menuTime () {

      state = state.copy(

         expendedTime = !state.expendedTime,

         expendedProject = false,

         isVisibilityCalendar = false

      )

   }

   fun deleteSelectedProject () {

      state = state.copy(

         selectedProject = null

      )

   }

   fun selectProject ( item: ProjectResponseModel ) {

   state = state.copy(

      selectedProject = item,

      expendedProject = false

   )

   }

   fun selectHour ( hour: String ) {

      state = state.copy(

         hour = hour

      )

   }

   fun selectMinutes ( minutes: String ) {

      state = state.copy(

         minutes = minutes

      )

   }

   fun inputTextProject ( text: String, list: List<ProjectResponseModel> ) {

      state = state.copy(

         projects = text,

         filteredListProjects = list

      )

   }

   fun inputTextDescription ( text: String ) {

      state = state.copy (

         description = text

      )

   }

   fun setScreen ( listProjects: List<ProjectResponseModel> ) {

      if ( state.isSet ) {

         state = state.copy(

            filteredListProjects = listProjects,

            isSet = false

         )

      }

   }

  fun openCloseCalendar () {

     state = state.copy(

        isVisibilityCalendar = !state.isVisibilityCalendar,

        expendedTime = false,

        expendedProject = false

     )

  }

}