package com.project.tape.datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.contragent_network.ContragentClient
import com.project.network.projects_network.ProjectClient
import com.project.network.tape_network.TapeClient
import com.project.tape.domain.repository.TapeClientApi
import com.project.tape.model.BlockModel
import com.project.tape.model.ContragentModel
import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.EntitiesModel
import com.project.tape.model.ProjectModel
import com.project.tape.model.ProjectResponseModel
import com.project.tape.model.TapeResponseModel

class TapeClientImpl(

    val sharedPrefsApi: SharedPrefsApi,

    val tapeClient: TapeClient,

    val projectClient: ProjectClient,

    val contragentClient: ContragentClient

): TapeClientApi {

    override suspend fun getToken(): String {

        return sharedPrefsApi.getToken()?:""

    }

    override suspend fun getVideo(): List<TapeResponseModel> {

        tapeClient.init(getToken())

        return tapeClient.getVideo().map {

            TapeResponseModel(

                id = it.id,
                name = it.name,
                text = it.text,
                created_at = it.created_at,
                updated_at = it.updated_at,
                creater_id = it.creater_id,
                company_id = it.company_id,
                contragent_id = it.contragent_id,
                image = it.image,
                del = it.del,
                video = it.video,
                position_vertical = it.position_vertical,
                project_id = it.project_id,
                contragent = if ( it.contragent != null ) ContragentModel(

                id = it.contragent!!.id,
                    name = it.contragent!!.name,
                    details = it.contragent!!.details

                ) else null,
                project = if ( it.project != null ) ProjectModel(

                    id = it.project!!.id,
                    name = it.project!!.name,
                    description = it.project!!.description

                ) else null,

                blocks = it.blocks.map {

                    BlockModel(

                        id = it.id,
                        type = it.type,
                        content = it.content

                    )

                }

            )

        }

    }

    override suspend fun getProjects(): List<ProjectResponseModel> {

        projectClient.init(getToken())

        return projectClient.getProjects().map {

         ProjectResponseModel(

             id = it.id,
             company_id = it.company_id,
             name = it.name,
             creater_id = it.creater_id,
             created_at = it.created_at,
             updated_at = it.updated_at,
             entity_client_id = it.entity_client_id,
             entity_id = it.entity_id,
             active = it.active

         )

        }

    }

    override suspend fun getContragents(): List<ContragentsResponseModel> {

        return contragentClient.getContragents().map {

            ContragentsResponseModel(

                id = it.id,
                name = it.name,
                ui = it.ui,
                own = it.own,
                entities = it.entits?.map {

                    EntitiesModel(

                        id = it.id,
                        name = it.name,
                        ui = it.ui

                    )

                }

            )

        }

    }

}