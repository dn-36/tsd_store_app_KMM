package org.example.project.presentation.project_control.viewmodel

import com.project.project_conterol.model.ServiceModel
import kotlinx.coroutines.CoroutineScope

sealed class ProjectControlIntents {

    object Back:ProjectControlIntents()

    object BackFromDataEntry:ProjectControlIntents()

    data class SetScreen( val coroutineScope: CoroutineScope ):ProjectControlIntents()

    data class OpenCreateDataEntryComponent(

        val coroutineScope: CoroutineScope ):ProjectControlIntents()

    data class OpenUpdateDataEntryComponent(

        val coroutineScope: CoroutineScope, val item: ServiceModel ):ProjectControlIntents()

    data class OpenDescription( val index: Int ):ProjectControlIntents()

    data class CreateProjectControl( val coroutineScope: CoroutineScope, val  text:String,

                                     val data: String, val time: String,

                                     val project_id: String  ):ProjectControlIntents()

    data class UpdateProjectControl( val coroutineScope: CoroutineScope, val ui: String,

                                     val  text:String, val data: String, val time: String,

                                     val project_id: String  ):ProjectControlIntents()
}