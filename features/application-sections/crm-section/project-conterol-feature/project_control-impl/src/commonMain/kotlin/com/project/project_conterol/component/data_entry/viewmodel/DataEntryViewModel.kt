package com.project.project_conterol.component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ServiceModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
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

         is DataEntryIntents.SetScreen -> setScreen(intent.listProjects, intent.item)

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

   fun setScreen ( listProjects: List<ProjectResponseModel>, item: ServiceModel? ) {

      var newHour = "00"

      var newMinutes = "00"

      var newDate: LocalDate? = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

      // Разделение времени
      if (!item?.time.isNullOrEmpty()) {
         val parts = item?.time!!.split(":")
         if (parts.size >= 2) { // Проверяем, что хотя бы часы и минуты есть
            newHour = parts[0]
            newMinutes = parts[1]
         }
      }

      // Преобразование строки даты в LocalDate
      if (!item?.data.isNullOrEmpty()) {
         newDate = item!!.data!!.toLocalDate()
      }

      if ( state.isSet ) {

         state = state.copy(

            filteredListProjects = listProjects,

            isSet = false,

            selectedProject = if ( item != null ) listProjects.find { it.id == item.project_id }

            else null,

            hour = newHour,

            minutes = newMinutes,

            date = newDate,

            description = if ( item != null ) item.text?:"" else ""


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