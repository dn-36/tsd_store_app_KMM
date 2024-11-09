package com.project.project_conterol

import cafe.adriel.voyager.core.screen.Screen
import com.project.`menu-crm-api`.ProjectControlScreenApi
import com.project.project_conterol.screen.ProjectControlScreen

class ProjectControlImpl: ProjectControlScreenApi {

    override fun projectControl(): Screen {

        return ProjectControlScreen()

    }

}