package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import domain.usecases.GetIncomingCRMUseCase
import domain.usecases.GetOutgoingCRMUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class CRMViewModel (

    val getIncomingCRMUseCase: GetIncomingCRMUseCase,

    val getOutgoingCRMUseCase: GetOutgoingCRMUseCase

): ViewModel() {

    var state by mutableStateOf( CRMState() )

    fun processIntents ( intent: CRMIntents ){

        when ( intent ) {

            is CRMIntents.getIncomingCRM -> {

            intent.coroutineScope.launch ( Dispatchers.IO ) {

                state = state.copy(

                    listIncomingCRM = getIncomingCRMUseCase.execute(),

                    listOutgoingCRM = getOutgoingCRMUseCase.execute()

                )

            }

                println(" CHECK CRM: ${state.listIncomingCRM} ")
                println(" CHECK OUTGOING CRM: ${state.listOutgoingCRM} ")

            }

        }

    }

}