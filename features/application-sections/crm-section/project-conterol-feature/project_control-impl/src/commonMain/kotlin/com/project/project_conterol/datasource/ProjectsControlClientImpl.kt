package com.project.project_conterol.datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.projects_control_network.ProjectControlClient
import com.project.network.projects_network.ProjectsClient
import com.project.project_conterol.domain.repository.ProjectControlClientApi
import com.project.project_conterol.model.ProjectModel
import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ProjectsControlResponseModel
import com.project.project_conterol.model.ServiceModel
import com.project.project_conterol.model.UserModel

class ProjectsControlClientImpl (

    private val projectsControlClient: ProjectControlClient,

    private val sharedPrefsApi: SharedPrefsApi,

    private val projectsClient: ProjectsClient

    ) : ProjectControlClientApi {

    override suspend fun getProjectsControl(): ProjectsControlResponseModel {

    projectsControlClient.init(sharedPrefsApi.getToken()?:"")

        val newList = projectsControlClient.getProjectControl()



        return ProjectsControlResponseModel(

            data = newList.data?.map {

                ServiceModel(

                    id = it.id,
                    user = UserModel(

                        id = it.user?.id,
                        name = it.user?.name

                    ),
                    project_id = it.project_id,
                    text = it.text,
                    price = it.price,
                    data = it.data,
                    time = it.time,
                    created_at = it.created_at,
                    updated_at = it.updated_at,
                    company_id = it.company_id,
                    type = it.type,
                    project = ProjectModel(

                        id = it.project?.id,
                        name = it.project?.name

                    ),
                    user_id = it.user_id

                )

            },
            balans = newList.balans

        )

    }

    override suspend fun getProjects(): List<ProjectResponseModel> {

        projectsClient.init(sharedPrefsApi.getToken()?:"")

        return projectsClient.getProjects().map {

            ProjectResponseModel(

                id = it.id,
                name = it.name,
                creater_id = it.creater_id,
                created_at = it.created_at,
                updated_at = it.updated_at,
                company_id = it.company_id,
                entity_id = it.entity_id,
                active = it.active,
                entity_client_id = it.entity_client_id

            )

        }

    }

}