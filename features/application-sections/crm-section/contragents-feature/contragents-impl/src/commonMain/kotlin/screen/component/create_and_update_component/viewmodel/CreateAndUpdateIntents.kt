package screen.component.create_and_update_component.viewmodel

import screen.model.ContragentResponseModel

sealed class CreateAndUpdateIntents {

    data class InputText ( val text: String ): CreateAndUpdateIntents()

    data class SetScreen ( val item: ContragentResponseModel? ): CreateAndUpdateIntents()

}