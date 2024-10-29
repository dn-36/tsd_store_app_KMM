package screens.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform
import screens.domain.usecases.CreateContragentUseCase
import screens.domain.usecases.DeleteContragentUseCase
import screens.domain.usecases.GetContragentsUseCase
import screens.domain.usecases.UpdateContragentUseCase
import screens.model.ContragentResponseModel

class ContragentsViewModel (

    val getContagentsUseCase: GetContragentsUseCase,

    val deleteContragentUseCase: DeleteContragentUseCase,

    val createContragentsUseCase: CreateContragentUseCase,

    val updateContragentUseCase: UpdateContragentUseCase

): NetworkViewModel() {

    var state by mutableStateOf( ContragentsState() )

  fun processIntents( intent: ContragentsIntents )  {

      when ( intent ) {

          is ContragentsIntents.GetContragents -> {

              intent.coroutineScope.launch ( Dispatchers.IO ) {

                  if ( state.isSet ) {

                      val listContragents = getContagentsUseCase.execute()

                      state = state.copy(

                          listAllContragents = listContragents.toMutableList(),

                          isSet = false

                      )

                  }

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.DeleteContragent -> {

              intent.coroutineScope.launch ( Dispatchers.IO ) {

                  deleteContragentUseCase.execute( state.updatedItem!!.id!! )

                  val listContragents = getContagentsUseCase.execute()

                  state = state.copy(

                      isVisibleDeleteComponent = 0f,

                      listAllContragents = listContragents.toMutableList()

                  )

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.OpenCreateOrUpdateComponent -> { createOrUpdateButton( intent.item,

              intent.index ) }

          is ContragentsIntents.Create -> {

              intent.coroutineScope.launch ( Dispatchers.IO ) {

                  createContragentsUseCase.execute( intent.name )

                  val listContragents = getContagentsUseCase.execute()

                  state = state.copy(

                      listAllContragents = listContragents.toMutableList(),

                      isVisibleCreateAndUpdateComponent = 0f

                      )

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.Update -> {

              intent.coroutineScope.launch ( Dispatchers.IO ) {

              updateContragentUseCase.execute( intent.name, state.updatedItem!!.id?:0 )

                  val listContragents = getContagentsUseCase.execute()

                  state = state.copy(

                      listAllContragents = listContragents.toMutableList(),

                      isVisibleCreateAndUpdateComponent = 0f

                  )

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.CanselComponent -> { canselComponent() }

          is ContragentsIntents.Back -> { back() }

          is ContragentsIntents.OpenDeleteComponent -> { openDeleteComponent( intent.item ) }

          is ContragentsIntents.NoDelete -> { noDelete() }

      }

  }

    fun createOrUpdateButton ( item: ContragentResponseModel?, index: Int ) {

        state = state.copy(

            isVisibleCreateAndUpdateComponent = 1f,

            updatedItem = item,

            index = index

        )

    }

    fun canselComponent () {

        state = state.copy(

            isVisibleCreateAndUpdateComponent = 0f

        )

    }

    fun back () {

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

    }

    fun openDeleteComponent ( item: ContragentResponseModel ) {

        state = state.copy(

            isVisibleDeleteComponent = 1f,

            updatedItem = item

        )

    }

    fun noDelete () {

        state = state.copy(

            isVisibleDeleteComponent = 0f,

            updatedItem = null

        )

    }

}