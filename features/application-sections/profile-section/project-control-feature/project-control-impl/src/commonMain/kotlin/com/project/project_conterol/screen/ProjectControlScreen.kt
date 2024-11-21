package com.project.project_conterol.screen


import com.project.core_app.network_base_screen.NetworkScreen
import com.project.project_conterol.component.ProjectControlComponent
import org.koin.mp.KoinPlatform.getKoin

class ProjectControlScreen : NetworkScreen (

    ProjectControlComponent(getKoin().get())

)