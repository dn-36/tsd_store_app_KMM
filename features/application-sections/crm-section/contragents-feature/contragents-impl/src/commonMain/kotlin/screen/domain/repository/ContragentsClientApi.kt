package screen.domain.repository

import screen.model.ContragentResponseModel

interface ContragentsClientApi {

   suspend fun getContragents(): List<ContragentResponseModel>

   suspend fun deleteContragents( id: Int )

   suspend fun createContragents( name: String )

   suspend fun updateContragent(name: String, id: Int )

}