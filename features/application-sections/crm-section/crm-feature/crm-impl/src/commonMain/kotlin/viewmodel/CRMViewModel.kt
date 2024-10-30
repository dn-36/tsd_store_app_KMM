package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import com.project.network.contragent_network.ContragentClient
import domain.usecases.GetIncomingCRMUseCase
import domain.usecases.GetOutgoingCRMUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class CRMViewModel (

    val getIncomingCRMUseCase: GetIncomingCRMUseCase,

    val getOutgoingCRMUseCase: GetOutgoingCRMUseCase

): NetworkViewModel() {

    var state by mutableStateOf( CRMState() )

    fun processIntents ( intent: CRMIntents ){

        when ( intent ) {

            is CRMIntents.getIncomingCRM -> {

                if ( state.isSet ) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        state = state.copy(

                            listIncomingCRM = getIncomingCRMUseCase.execute(),

                            listOutgoingCRM = getOutgoingCRMUseCase.execute(),

                            isSet = false

                        )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }
                }

            }

            is CRMIntents.Back -> { back() }

            is CRMIntents.SelectTypeCRM -> { selectTypeCRM( intent.index ) }

        }

    }

    fun back () {

         println(" CHECK CRM: ${state.listIncomingCRM} ")

         println(" CHECK OUTGOING CRM: ${state.listOutgoingCRM} ")

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

    }

    fun selectTypeCRM ( index: Int ) {

        when ( index ) {

            1 -> { state = state.copy(

                isIncoming = true,

                isOutgoing = false

            ) }

            2 -> { state = state.copy(

                isIncoming = false,

                isOutgoing = true

            ) }

        }

    }

}