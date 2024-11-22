package screen.component.create_and_update_component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import screen.model.ContragentResponseModel

class CreateAndUpdateViewModel:ViewModel() {

    var state by mutableStateOf(CreateAndUpdateState())

    fun processIntents (intent: CreateAndUpdateIntents) {

        when ( intent ) {

            is CreateAndUpdateIntents.InputText -> { inputText( intent.text ) }

            is CreateAndUpdateIntents.SetScreen -> { setScreen( intent.item ) }

        }

    }

    fun inputText ( text:String ) {

        state = state.copy(

            name = text

        )

    }

    fun setScreen ( item: ContragentResponseModel? ) {

        if ( state.isSet ) {

            state = state.copy(

                name = if (item != null) item.name ?: "" else "",

                isSet = false

            )

        }

    }

}