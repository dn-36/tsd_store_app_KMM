package com.project.tape.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.core_app.utils.imageBitmapToBase64
import com.project.tape.domain.usecases.CreatePhotoOrVideoUseCase
import com.project.tape.domain.usecases.GetContragentsUseCase
import com.project.tape.domain.usecases.GetProjectsUseCase
import com.project.tape.domain.usecases.GetVideoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class TapeViewModel (

    val getVideoUseCase: GetVideoUseCase,

    val getProjectsUseCase: GetProjectsUseCase,

    val getContragentsUseCase: GetContragentsUseCase,

    val createPhotoOrVideoUseCase: CreatePhotoOrVideoUseCase

): NetworkViewModel() {

    var state by mutableStateOf(TapeState())

   fun processIntents( intent: TapeIntents ) {

       when ( intent ) {

           is TapeIntents.SetScreen -> {

               intent.coroutineScope.launch ( Dispatchers.IO ) {

                   if ( state.isUsed ) {

                       state = state.copy(

                           listVideo = getVideoUseCase.execute(),

                           isUsed = false

                       )
                   }

                   setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

               }

           }

           is TapeIntents.OpenCreateDataEntry -> {

               setStatusNetworkScreen(StatusNetworkScreen.LOADING)

               intent.coroutineScope.launch( Dispatchers.IO ) {

                   state = state.copy(

                       listProjects = getProjectsUseCase.execute(),

                       listContragents = getContragentsUseCase.execute(),

                       isVisibilityDataEntry = true

                   )

                   setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

               }

           }

           is TapeIntents.BackFromDataEntry -> backFromDataEntry()

           is TapeIntents.CreateElement -> {

               setStatusNetworkScreen(StatusNetworkScreen.LOADING)

               intent.coroutineScope.launch( Dispatchers.IO ) {

                   val imageBase64 = if ( intent.image != null ) imageBitmapToBase64(intent.image)

                   else ""

                   createPhotoOrVideoUseCase.execute(intent.name,intent.description,

                       imageBase64, "jpg",video = "", format_video = "",

                       intent.idContragent, intent.idProject)

                   state = state.copy(

                       isVisibilityDataEntry = false

                   )

                   setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

               }

           }

       }

   }

    fun backFromDataEntry() {

        state = state.copy(

            isVisibilityDataEntry = false

        )

    }

}