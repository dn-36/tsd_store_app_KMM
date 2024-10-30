package viewmodel

import androidx.lifecycle.ViewModel
import domain.usecases.GetIncomingCRMUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class CRMViewModel (

    val getIncomingCRMUseCase: GetIncomingCRMUseCase

): ViewModel() {

    fun processIntents ( intent: CRMIntents ){

        when ( intent ) {

            is CRMIntents.getIncomingCRM -> {

            intent.coroutineScope.launch ( Dispatchers.IO ) {

                println(" CHECK CRM: ${getIncomingCRMUseCase.execute()} ")

            }

            }

        }

    }

}