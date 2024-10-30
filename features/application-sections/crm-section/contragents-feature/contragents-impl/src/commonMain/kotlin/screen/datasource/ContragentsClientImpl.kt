package screen.datasource

import com.project.network.contragent_network.ContragentClient
import screen.domain.repository.ContragentsClientApi
import screen.model.ContragentResponseModel
import screen.model.EntityModel

class ContragentsClientImpl (

    val client: ContragentClient

): ContragentsClientApi {

    override suspend fun getContragents (): List<ContragentResponseModel> {

        println("ALL CONTRAGENTS: ${ client.getContragents() }")

        val newList = client.getContragents().map {

            ContragentResponseModel(

                id = it.id?:0,
                 name = it.name,
                ui = it.ui,
                own = it.own,
                entits = it.entits?.map {

                    EntityModel(

                        id = it.id,

                        name = it.name,

                        ui = it.ui

                    )

                }



            )

        }

        return newList

    }

    override suspend fun deleteContragents ( id:Int ) {

    client.deleteContragent( id )

    }

    override suspend fun createContragents ( name: String ) {

        client.createContragent ( name )

    }

    override suspend fun updateContragent ( name: String, id: Int ) {

    client.updateContragent( id, name )

    }

}