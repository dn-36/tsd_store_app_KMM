package screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.chats.ProfileScreensApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform
import screen.domain.usecases.CreateContragentUseCase
import screen.domain.usecases.DeleteContragentUseCase
import screen.domain.usecases.GetContragentsUseCase
import screen.domain.usecases.UpdateContragentUseCase
import screen.model.ContragentResponseModel

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

                      state = state.copy(

                          listContragents = getContagentsUseCase.execute(),

                          listFilteredContragents = getContagentsUseCase.execute(),

                          isSet = false

                      )

                  }

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.DeleteContragent -> {

              setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

              intent.coroutineScope.launch ( Dispatchers.IO ) {

                  deleteContragentUseCase.execute( state.updatedItem!!.id!! )

                  state = state.copy(

                      isVisibleDeleteComponent = 0f,

                      listContragents = getContagentsUseCase.execute(),

                      listFilteredContragents = getContagentsUseCase.execute(),

                  )

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.OpenCreateOrUpdateComponent -> { createOrUpdateButton( intent.item,

              intent.index ) }

          is ContragentsIntents.Create -> {

              setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

              intent.coroutineScope.launch ( Dispatchers.IO ) {

                  createContragentsUseCase.execute( intent.name )

                  state = state.copy(

                      listContragents = getContagentsUseCase.execute(),

                      listFilteredContragents = getContagentsUseCase.execute(),

                      isVisibleCreateAndUpdateComponent = 0f

                      )

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.Update -> {

              setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

              intent.coroutineScope.launch ( Dispatchers.IO ) {

              updateContragentUseCase.execute( intent.name, state.updatedItem!!.id?:0 )

                  state = state.copy(

                      listContragents = getContagentsUseCase.execute(),

                      listFilteredContragents = getContagentsUseCase.execute(),

                      isVisibleCreateAndUpdateComponent = 0f

                  )

                  setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

              }

          }

          is ContragentsIntents.CanselComponent -> { canselComponent() }

          is ContragentsIntents.OpenDeleteComponent -> { openDeleteComponent( intent.item ) }

          is ContragentsIntents.NoDelete -> { noDelete() }

          is ContragentsIntents.InputTextSearchComponent -> inputTextSearchComponent(intent.text)

          is ContragentsIntents.LongPressItem -> longPressItem(intent.index)

          is ContragentsIntents.OnePressItem -> onePressItem()

          is ContragentsIntents.Back -> back()

      }

  }

    fun createOrUpdateButton ( item: ContragentResponseModel?, index: Int ) {

        state = state.copy(

            isVisibleCreateAndUpdateComponent = 1f,

            updatedItem = item,

            index = index

        )

    }

    fun back () {

        val profileScreen: ProfileScreensApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push(profileScreen.profile())

    }

    fun canselComponent () {

        state = state.copy(

            isVisibleCreateAndUpdateComponent = 0f

        )

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

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listContragents.filter {

            val companyName = it.name.orEmpty()

            companyName.contains(text, ignoreCase = true)

        }

        state = state.copy(

            listFilteredContragents = newList

        )

        println("Text ${text}")
        println("NewList ${newList}")

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listContragents.size) { 0F }

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listContragents.size) { 0F }

        state = state.copy(

            listAlphaTools = newList

        )

    }

}